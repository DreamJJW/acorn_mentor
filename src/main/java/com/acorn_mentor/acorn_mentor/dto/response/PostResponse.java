package com.acorn_mentor.acorn_mentor.dto.response;

import com.acorn_mentor.acorn_mentor.dto.PostDto;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.acorn_mentor.acorn_mentor.domain.Post}
 */
public record PostResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdDate,
        String email,
        String nickname
)

{
    public static PostResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname) {
        return new PostResponse(id, title, content, hashtag, createdAt, email, nickname);
    }

    public static PostResponse from(PostDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return new PostResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdDate(),
                dto.userAccountDto().email(),
                nickname
        );
    }
}