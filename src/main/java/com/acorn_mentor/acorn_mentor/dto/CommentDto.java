package com.acorn_mentor.acorn_mentor.dto;

import com.acorn_mentor.acorn_mentor.domain.Comment;
import com.acorn_mentor.acorn_mentor.domain.Post;
import com.acorn_mentor.acorn_mentor.domain.UserAccount;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.acorn_mentor.acorn_mentor.domain.Comment}
 */
public record CommentDto(
        Long id,
        Long postId,
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime createdDate,
        String createdBy,
        LocalDateTime modifiedDate,
        String modifiedBy
) {

    public static CommentDto of(Long id, Long postId, UserAccountDto userAccountDto, String content, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
        return new CommentDto(id, postId, userAccountDto, content, createdDate, createdBy, modifiedDate, modifiedBy);
    }

    public static CommentDto from(Comment entity) {
        return new CommentDto(
                entity.getId(),
                entity.getPost().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getContent(),
                entity.getCreatedDate(),
                entity.getCreatedBy(),
                entity.getModifiedDate(),
                entity.getModifiedBy()
        );
    }

    public Comment toEntity(Post entity) {
        return Comment.of(
                userAccountDto.toEntity(),
                content,
                entity
        );
    }
}