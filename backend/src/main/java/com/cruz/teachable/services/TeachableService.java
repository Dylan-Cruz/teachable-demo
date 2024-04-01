package com.cruz.teachable.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cruz.teachable.api.TeachableClient;
import com.cruz.teachable.model.teachable.Course;
import com.cruz.teachable.model.teachable.Enrollment;
import com.cruz.teachable.model.teachable.PagedResponse;
import com.cruz.teachable.model.teachable.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeachableService {

    private final TeachableClient client;

    @Autowired
    public TeachableService(TeachableClient client) {
        this.client = client;
    }

    public Flux<Course> getAllCourses() {
        return client.getCourses(1, null)
                .expand(response -> response.getMeta().page() < response.getMeta().number_of_pages()
                        ? client.getCourses(response.getMeta().page() + 1, null)
                        : Mono.empty())
                .flatMapIterable(PagedResponse::getData);
    }

    public Flux<User> getAllUsers() {
        return client.getUsers(1, null)
                .expand(response -> response.getMeta().page() < response.getMeta().number_of_pages()
                        ? client.getUsers(response.getMeta().page() + 1, null)
                        : Mono.empty())
                .flatMapIterable(PagedResponse::getData);
    }

    public Flux<Enrollment> getAllEnrollmentsForCourse(int courseId) {
        return client.getEnrollments(courseId, 1, null)
                .expand(response -> response.getMeta().page() < response.getMeta().number_of_pages()
                        ? client.getEnrollments(courseId, response.getMeta().page() + 1, null)
                        : Mono.empty())
                .flatMapIterable(PagedResponse::getData);
    }
}
