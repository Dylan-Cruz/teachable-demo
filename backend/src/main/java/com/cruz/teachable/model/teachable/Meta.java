package com.cruz.teachable.model.teachable;

public record Meta(
        int total,
        int page,
        int from,
        int to,
        int per_page,
        int number_of_pages) {

}