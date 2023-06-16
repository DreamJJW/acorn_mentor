package com.acorn_mentor.acorn_mentor.dto.response;

import com.acorn_mentor.acorn_mentor.dto.PostAndCommentsDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.acorn_mentor.acorn_mentor.domain.Post}
 */
public record PostAndCommentsResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdDate,
        String email,
        String nickname,
        Set<CommentResponse> commentResponses
) {
    public static PostAndCommentsResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdDate,
                                             String email, String nickname, Set<CommentResponse> commentResponses) {
        return new PostAndCommentsResponse(id, title, content, hashtag, createdDate, email, nickname, commentResponses);
    }

    public static PostAndCommentsResponse from(PostAndCommentsDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return new PostAndCommentsResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdDate(),
                dto.userAccountDto().email(),
                nickname,
                dto.commentDtos().stream().map(CommentResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }
}