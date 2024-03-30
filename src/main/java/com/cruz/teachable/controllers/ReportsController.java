package com.cruz.teachable.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cruz.teachable.api.TeachableClient;
import com.cruz.teachable.model.Course;
import com.cruz.teachable.model.Enrollment;

@RestController
public class ReportsController {

    @Autowired
    private TeachableClient client;

    @GetMapping("/report")
    public void getReport() {
        // call the endpoints to get the courses and users
        var coursesMono = client.getCourses();
        var usersMono = client.getUsers();

        var coursesToEnrollmentsMap = new HashMap<Course, List<Enrollment>>();
        coursesMono.subscribe(courses -> {
            
        })

        // combine the results
        coursesMono.zipWith(usersMono).subscribe(tuple -> {
            var courses = tuple.getT1();
            var users = tuple.getT2();

        });

    }
}
