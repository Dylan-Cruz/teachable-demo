package com.cruz.teachable.model;

import java.util.List;

import com.cruz.teachable.model.teachable.User;

public record EnrolleeReportCourse(
        String name,
        String heading,
        List<User> enrolledUsers) {
}
