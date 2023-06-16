package com.acorn_mentor.acorn_mentor.dto.response;

import com.acorn_mentor.acorn_mentor.dto.CommentDto;
import com.acorn_mentor.acorn_mentor.dto.PostDto;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.acorn_mentor.acorn_mentor.domain.Post}
 */
public record CommentResponse(
        Long id,
        String content,
        LocalDateTime createdDate,
        String email,
        String nickname
) {

    public static CommentResponse of(Long id, String content, LocalDateTime createdDate, String email, String nickname) {
        return new CommentResponse(id,  content, createdDate, email, nickname);
    }

    public static CommentResponse from(CommentDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return new CommentResponse(
                dto.id(),
                dto.content(),
                dto.createdDate(),
                dto.userAccountDto().email(),
                nickname
        );
    }
}