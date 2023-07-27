package com.ddungja.petmily.post.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MainPostsResponse {
    private final List<String> filter;
    private final List<MainPostResponse> posts;

    private final int totalPages;
    private final long totalElements;

    @Builder
    public MainPostsResponse(List<String> filter, List<MainPostResponse> posts, int totalPages, long totalElements) {
        this.filter = filter;
        this.posts = posts;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public static MainPostsResponse from(List<String> filter, List<MainPostResponse> mainPostResponses, int totalPages, long totalElements){
        return MainPostsResponse.builder()
                .filter(filter)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .posts(mainPostResponses)
                .build();
    }
}
