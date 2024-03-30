package com.cruz.teachable.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cruz.teachable.api.TeachableClient;
import com.cruz.teachable.model.EnrolleeReportCourse;
import com.cruz.teachable.model.teachable.Course;
import com.cruz.teachable.model.teachable.User;
import com.cruz.teachable.services.TeachableService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReportsController {

    @Autowired
    private TeachableClient client;

    @Autowired
    private TeachableService service;

    @GetMapping("/report")
    public Flux<List<EnrolleeReportCourse>> getReport() {
        // start a call to get all the users in the school
        Flux<User> userFlux = service.getAllUsers(); // assuming getUsers() returns a Flux<User>
        Mono<Map<Integer, User>> userMap = userFlux.collectMap(User::id);

        // start a call to get all the courses in the school
        Flux<Course> courseFlux = service.getAllCourses();

    }
}
