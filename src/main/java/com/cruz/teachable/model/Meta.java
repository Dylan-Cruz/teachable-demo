package com.cruz.teachable.model;

public record Meta(
        int page,
        int total,
        int number_of_pages,
        int from,
        int to,
        int per_page) {

}