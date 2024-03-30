package com.cruz.teachable.model.teachable;

public record Meta(
        int page,
        int total,
        int number_of_pages,
        int from,
        int to,
        int per_page) {

}