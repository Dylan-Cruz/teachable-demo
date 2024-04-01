package com.cruz.teachable.model.teachable;

import java.util.List;

public record Course(
        int id,
        String name,
        String heading,
        String description,
        boolean is_published,
        String image_url,
        List<LectureSection> lectureSections) {

    public Course(int id, String name, String heading) {
        this(id, name, heading, null, true, null, List.of());
    }
}
