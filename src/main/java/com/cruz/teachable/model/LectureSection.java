package com.cruz.teachable.model;

import java.util.List;

public record LectureSection(
        int id,
        String name,
        boolean is_published,
        int position,
        List<Lecture> lectures) {

}
