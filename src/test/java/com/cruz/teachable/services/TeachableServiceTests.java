package com.cruz.teachable.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.cruz.teachable.api.TeachableClient;
import com.cruz.teachable.model.teachable.Course;
import com.cruz.teachable.model.teachable.Meta;
import com.cruz.teachable.model.teachable.PagedResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class TeachableServiceTests {

    private TeachableService service;
    private TeachableClient mockClient;

    @BeforeEach
    public void setUp() {
        mockClient = Mockito.mock(TeachableClient.class);
        System.out.println("mockClient: " + mockClient);
        service = new TeachableService(mockClient);
    }

    @Test
    public void testGetAllCoursesAndPagesCorrectly() {
        // Arrange
        PagedResponse<List<Course>> page1 = new PagedResponse<>();
        page1.setMeta(new Meta(4, 1, 1, 2, 2, 2));
        page1.setData(
                Arrays.asList(
                        new Course(1, "Test Course 1", "Test Course 1 Header"),
                        new Course(2, "Test Course 2", "Test Course 2 Header")));

        PagedResponse<List<Course>> page2 = new PagedResponse<>();
        page2.setMeta(new Meta(4, 2, 3, 4, 2, 2));
        page2.setData(
                Arrays.asList(
                        new Course(3, "Test Course 3", "Test Course 3 Header"),
                        new Course(4, "Test Course 4", "Test Course 4 Header")));

        Mockito.when(mockClient.getCourses(eq(1), any()))
                .thenReturn(Mono.just(page1));
        Mockito.when(mockClient.getCourses(eq(2), any()))
                .thenReturn(Mono.just(page2));

        // Act
        Flux<Course> result = service.getAllCourses();

        // Assert
        StepVerifier.create(result)
                .expectNextCount(4)
                .verifyComplete();

        Mockito.verify(mockClient, Mockito.times(1)).getCourses(eq(1), any());
        Mockito.verify(mockClient, Mockito.times(1)).getCourses(eq(2), any());
    }

    @Test
    public void testGetAllCoursesEmptyResponse() {
        // Arrange
        PagedResponse<List<Course>> page1 = new PagedResponse<>();
        page1.setMeta(new Meta(0, 1, 0, 0, 0, 1));
        page1.setData(
                List.of());

        Mockito.when(mockClient.getCourses(eq(1), any())).thenReturn(Mono.just(page1));

        // Act
        Flux<Course> result = service.getAllCourses();

        // Assert
        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }
}
