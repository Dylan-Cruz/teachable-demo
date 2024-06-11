# Teachable Demo
Hello Teachable reviewers,  
This is my submission for the take home assessment to generate a report listing all courses with the students enrolled in that course. I'll provide a brief write up to walk through my process after the Running Locally section. 
## Running locally
There are two ways you can run the server. Both require installing Java which is easiest using SDKMan (aka Java's version of RVM) found [here](https://sdkman.io/). 
### Build (optional)
I inlcluded a build in case there are issues with building locally so this is optional. To build:
1. install java 21.0.2 (openJDK)
2. install gradle 8.7 (can also be installed via SDKman)
3. cd into the root of the project and run `gradle build`
4. tests will be ran and build output will be in build/libs/
### Run
1. install java 21.0.2 if not installed (openJDK)
2. cd into the root of the project and run `java -jar build/libs/teachable-0.0.1-SNAPSHOT.jar`
### If all else fails, checkout the demo video
If for some reason you can't build or run the application locally, you can watch the included demo video at the root of the project. 
### Testing
There are two methods you can use to test my work:
1. You can make a get request to `http://localhost:8080/report/enrollment`.
2. I included a very barebones vue.js page that will make the call and display the data. You can load this by entering `http://localhost:8080/index.js`
## Write Up
### Approach
After reading the problem statement and studying the APIs (nice docs by the way) it was clear we could approach this from two different vectors. You could get all users, then for every user get the courses they're enrolled in and merge the data into a map where the course ids are keys. This however would result in calls that are potentially unnecessary in the event a user is not enrolled in any courses. The second approach which is the one I went with was to get all users and courses. Then for each course, get the enrollments and look up the user details. This would result in fewer calls.
### Solution
When I take these tests I try to use a technology or framework I've never used or would like to learn more about. In this case I focused on building the api with Spring Web Flux which is a fully asynchronous and non-blocking reactive framework. Leveraging Spring Web Flux results in a performance increase when compared to a one thread per request model found in traditional Java MVC frameworks. While not strictly necessary here, it was something I could do to drive my skills forward on a modern framework and get a refresher on reactive programming. 
#### Backend
All files in this explanation share the root path of `src/main/java/com/cruz/teachable`
- **api/TeachableClient**: A non-blocking api client for the public Teachable API with paging parameters included. Provides /users, /courses, and /courses/{id}/enrollments methods. 
- **services/TeachableService**: an abstraction on the teachable api client that iterates over all pages.
- **controllers/ReportsController**: the reactive endpoint implmentation for the get /reports/enrollment route. 
    1. Starts a call to getAllUsers()
    2. Starts a call to getAllCourses()
    3. Assemble the two calls into a single producer that will emit a tuple of userMap (hash table of userId to user) and course.
    4. flatMap will subscribe to the producer and kick off the calls that will emit when returned
    5. Once the tuple is emitted, for each course...
        1. Get all enrollments via getAllEnrollmentsForCourse()
        2. Map each user id returned to the matching user in the userMap
        3. Collect them into a list to build the EnrollmentReportCourse object
    6. Collect the courses into a list and map the last stream to an EnrollmentReport
    7. Return the EnrollmentReport Mono which will automatically be unboxed by Spring Framework
    8. In the event of any error, a generic 500 is automatically returned by Spring and the error is logged to the console.
- **model/teachable/\***: A collection of domain objects used to unmarshal teachable api responses.
- **model/\***: Domain objects that represent an enrollment report.
#### 'Frontend'
I took this opportunity to teach myself vue.js. In respect to the time constraint, all I had time to do was return a page with bare html. The build output of that project is included statically in the server jar file.
### Limitations and Bad Practices
I like to summarize shortcuts or concessions I made for these projects due to time. 
1. I included testing only for the courses method stack in the api and service objects. This was to demonstrate that I can comprehensively test with a mock server and an object mocking framework without spending time copy and pasting the same tests for the other methods. I figured more isn't always better here.
2. I didn't write a test for the controller as implementation testing usually breaks if any change is made rather than when something is really broken and it was time consuming.
3. CORs is disabled by default as this is just a dev project
4. The token and baseurl for the teachable client are somewhat but not truly externalized since this is a dev project. 
5. Ordering isn't guaranteed of the returned data set because asyncronous publishers are being combined into a single stream. This is something that can be fixed by using an operator built into the framework that waits for the publisher to complete before moving to the next one. This however results in what is essentially a syncronous call.
### Expansion
- The code in the ReportsController.getEnrollmentReport() could be abstracted to a service so that if the server needs to assemble the same data to generate a PDF export for example, the code is reusable. 
- Styling could be included on the frontend.
- A feature could be introduced to track where in each course most users unenroll giving actionable insight to the creator on what to change. Since access to the unenroll webhook wasn't available I planned to simulate it with a wrapper around the teachable unenroll api but ran out of time. 
