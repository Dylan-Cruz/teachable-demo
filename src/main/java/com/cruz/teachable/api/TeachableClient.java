package com.cruz.teachable.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.cruz.teachable.model.Course;

import reactor.core.publisher.Mono;

@Component
public class TeachableClient {
    private static final Logger log = LoggerFactory.getLogger(TeachableClient.class);
    private final WebClient webClient;

    @Value("${teachable.api.key}")
    private String apiKey;

    public TeachableClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://developers.teachable.com")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("apiKey", apiKey)
                .build();
    }

    public Mono<List<Course>> getCourses() {
        return this.webClient.get()
                .uri("/v1/courses")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Course>>() {
                })
                .onErrorResume(e -> {
                    log.error("Error occurred while fetching courses: ", e);
                    return Mono.empty();
                });
    }

}
