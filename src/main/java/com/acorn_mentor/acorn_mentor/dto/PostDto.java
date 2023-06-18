package com.acorn_mentor.acorn_mentor.dto;

import com.acorn_mentor.acorn_mentor.domain.Post;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.acorn_mentor.acorn_mentor.domain.Post}
 */
public record PostDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdDate,
        String createdBy,
        LocalDateTime modifiedDate,
        String modifiedBy
){
    public static PostDto of(Long id, UserAccountDto userAccountDto, String title, String content, String hashtag, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
        return new PostDto(id, userAccountDto, title, content, hashtag, createdDate, createdBy, modifiedDate, modifiedBy);
    }

    public static PostDto from(Post entity) {
        return new PostDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedDate(),
                entity.getCreatedBy(),
                entity.getModifiedDate(),
                entity.getModifiedBy()
        );
    }

    public Post toEntity(){
        return Post.of(
                userAccountDto.toEntity(),
                title,
                content,
                hashtag
        );
    }
}