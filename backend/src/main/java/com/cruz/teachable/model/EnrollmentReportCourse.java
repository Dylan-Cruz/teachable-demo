package com.cruz.teachable.model;

import java.util.List;

import com.cruz.teachable.model.teachable.Course;
import com.cruz.teachable.model.teachable.User;

public record EnrollmentReportCourse(
        String name,
        String heading,
        List<User> enrolledUsers) {

    public EnrollmentReportCourse(Course course, List<User> users) {

        this(course.name(), course.heading(), users);
    }
}
