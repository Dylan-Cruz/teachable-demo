package com.cruz.teachable.model;

import java.util.List;

import com.cruz.teachable.model.teachable.Course;
import com.cruz.teachable.model.teachable.User;

public record EnrollmentReportCourse(
        int id,
        String name,
        String heading,
        List<User> enrolledStudents) {

    public EnrollmentReportCourse(Course course, List<User> users) {

        this(course.id(), course.name(), course.heading(), users);
    }
}
