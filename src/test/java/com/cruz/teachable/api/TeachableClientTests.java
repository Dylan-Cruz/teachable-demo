package com.cruz.teachable.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;

import com.cruz.teachable.model.teachable.Course;
import com.cruz.teachable.model.teachable.PagedResponse;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class TeachableClientTests {

    private MockWebServer mockWebServer;
    private TeachableClient teachableClient;

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        WebClient.Builder webClientBuilder = WebClient.builder();
        teachableClient = new TeachableClient(webClientBuilder, mockWebServer.url("/").toString(), "");
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testGetCourses() {
        // Arrange
        String expectedResponse = "{\"courses\": [{\"id\": 1, \"name\": \"Course 1\"}]}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(expectedResponse)
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        // Act
        Mono<PagedResponse<List<Course>>> response = teachableClient.getCourses(1, 10);

        // Assert
        StepVerifier.create(response)
                .assertNext(pagedResponse -> {
                    List<Course> courses = pagedResponse.getData();
                    assertEquals(1, courses.size());
                    assertEquals(1, courses.get(0).id());
                    assertEquals("Course 1", courses.get(0).name());
                })
                .verifyComplete();
    }

    @Test
    public void testGetCoursesWithNoCourses() {
        // Arrange
        String expectedResponse = "{\"courses\": []}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(expectedResponse)
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        // Act
        Mono<PagedResponse<List<Course>>> response = teachableClient.getCourses(1, 10);

        // Assert
        StepVerifier.create(response)
                .assertNext(pagedResponse -> {
                    List<Course> courses = pagedResponse.getData();
                    assertEquals(0, courses.size());
                })
                .verifyComplete();
    }

    @Test
    public void testGetCoursesWithInvalidResponse() {
        // Arrange
        String expectedResponse = "{\"invalid\": \"response\"}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(expectedResponse)
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        // Act
        Mono<PagedResponse<List<Course>>> response = teachableClient.getCourses(1, 10);

        // Assert
        StepVerifier.create(response)
                .expectError()
                .verify();
    }

    @Test
    public void testGetCoursesWithServerError() {
        // Arrange
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        // Act
        Mono<PagedResponse<List<Course>>> response = teachableClient.getCourses(1, 10);

        // Assert
        StepVerifier.create(response)
                .expectError()
                .verify();
    }

    @Test
    public void testGetCoursesParamsPresent() throws InterruptedException {
        // Arrange
        String expectedResponse = "{\"courses\": [{\"id\": 1, \"name\": \"Course 1\"}]}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(expectedResponse)
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        // Act
        Mono<PagedResponse<List<Course>>> response = teachableClient.getCourses(1, 10);

        // Assert
        StepVerifier.create(response)
                .expectNextMatches(pagedResponse -> {
                    try {
                        String requestPath = mockWebServer.takeRequest().getPath();
                        return requestPath.contains("/courses?page=1&per=10");
                    } catch (InterruptedException e) {
                        return false;
                    }
                })
                .verifyComplete();
    }

    @Test
    public void testGetCoursesParamsNotPresent() {
        // Arrange
        String expectedResponse = "{\"courses\": [{\"id\": 1, \"name\": \"Course 1\"}]}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(expectedResponse)
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        // Act
        Mono<PagedResponse<List<Course>>> response = teachableClient.getCourses(null, null);

        // Assert
        StepVerifier.create(response)
                .expectNextMatches(pagedResponse -> {
                    try {
                        String requestPath = mockWebServer.takeRequest().getPath();
                        return requestPath.endsWith("/courses");
                    } catch (InterruptedException e) {
                        return false;
                    }
                })
                .verifyComplete();
    }
}
