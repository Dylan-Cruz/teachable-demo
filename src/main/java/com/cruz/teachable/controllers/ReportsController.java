package com.cruz.teachable.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cruz.teachable.model.EnrollmentReportCourse;
import com.cruz.teachable.model.teachable.Course;
import com.cruz.teachable.model.teachable.User;
import com.cruz.teachable.services.TeachableService;

import reactor.core.publisher.Flux;

@RestController
public class ReportsController {

    @Autowired
    private TeachableService service;

    @GetMapping("/report")
    public Flux<EnrollmentReportCourse> getReport() {
        // start a call to get all the users in the school
        Flux<User> userFlux = service.getAllUsers();

        // start a call to get all the courses in the school
        Flux<Course> courseFlux = service.getAllCourses();

        // combine the two calls, get the enrollments for each course, and then get the
        // user for each enrollment
        return userFlux.collectMap(User::id).zipWith(courseFlux.collectList())
                .flatMapMany(tuple -> {
                    Map<Integer, User> userMap = tuple.getT1();
                    List<Course> courses = tuple.getT2();

                    return Flux.fromIterable(courses)
                            .flatMap(course -> service.getAllEnrollmentsForCourse(course.id())
                                    .map(enrollment -> userMap.get(enrollment.user_id()))
                                    .collectList()
                                    .map(users -> new EnrollmentReportCourse(course, users)));
                });
    }
}
