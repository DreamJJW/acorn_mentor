package com.acorn_mentor.acorn_mentor.dto;

import com.acorn_mentor.acorn_mentor.domain.Post;

import java.util.LinkedHashSet;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.acorn_mentor.acorn_mentor.domain.Post}
 */
public record PostAndCommentsDto(
        Long id,
        UserAccountDto userAccountDto,
        Set<CommentDto> commentDtos,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdDate,
        String createdBy,
        LocalDateTime modifiedDate,
        String modifiedBy
) {
    public static PostAndCommentsDto of(Long id, UserAccountDto userAccountDto, Set<CommentDto> commentDtos, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new PostAndCommentsDto(id, userAccountDto, commentDtos, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static PostAndCommentsDto from(Post entity) {
        return new PostAndCommentsDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getComments().stream()
                        .map(CommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedDate(),
                entity.getCreatedBy(),
                entity.getModifiedDate(),
                entity.getModifiedBy()
        );
    }
}