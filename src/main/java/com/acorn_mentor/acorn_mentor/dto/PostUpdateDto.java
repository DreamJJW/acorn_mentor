package com.acorn_mentor.acorn_mentor.dto;
/**
 * DTO for {@link com.acorn_mentor.acorn_mentor.domain.Post}
 */
public record PostUpdateDto(
        String title,
        String content,
        String hashtag
){
    public static PostUpdateDto of(String title, String content, String hashtag) {
        return new PostUpdateDto(title, content, hashtag);
    }
}