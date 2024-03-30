package com.cruz.teachable.model;

import java.util.List;

public record Course(
        int id,
        String name,
        String heading,
        String description,
        boolean is_published,
        String image_url,
        List<LectureSection> lectureSections) {

}
