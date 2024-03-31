package com.cruz.teachable.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.cruz.teachable.model.teachable.Course;
import com.cruz.teachable.model.teachable.Enrollment;
import com.cruz.teachable.model.teachable.PagedResponse;
import com.cruz.teachable.model.teachable.User;

import reactor.core.publisher.Mono;

@Component
public class TeachableClient {
    private static final Logger log = LoggerFactory.getLogger(TeachableClient.class);
    private final WebClient webClient;

    public TeachableClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://developers.teachable.com")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("apiKey", "7JbSA3ep6XOMV3t8t7QXuXq9HS79Dwnr")
                .build();
    }

    public Mono<PagedResponse<List<Course>>> getCourses(Integer page, Integer per) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/courses")
                        .queryParamIfPresent("page", Optional.ofNullable(page))
                        .queryParamIfPresent("per", Optional.ofNullable(per))
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<PagedResponse<List<Course>>>() {
                });
    }

    public Mono<PagedResponse<List<User>>> getUsers(Integer page, Integer per) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/users")
                        .queryParamIfPresent("page", Optional.ofNullable(page))
                        .queryParamIfPresent("per", Optional.ofNullable(per))
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<PagedResponse<List<User>>>() {
                });
    }

    public Mono<PagedResponse<List<Enrollment>>> getEnrollments(int courseId, Integer page, Integer per) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/courses/{courseId}/enrollments")
                        .queryParamIfPresent("page", Optional.ofNullable(page))
                        .queryParamIfPresent("per", Optional.ofNullable(per))
                        .build(courseId))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<PagedResponse<List<Enrollment>>>() {
                });
    }
}
