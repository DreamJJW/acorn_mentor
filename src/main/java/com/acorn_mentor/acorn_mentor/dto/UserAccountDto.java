package com.acorn_mentor.acorn_mentor.dto;

import com.acorn_mentor.acorn_mentor.domain.UserAccount;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.acorn_mentor.acorn_mentor.domain.UserAccount}
 */
public record UserAccountDto(
        String userId,
        String userPw,
        String email,
        String nickname,
        LocalDateTime createdDate,
        String createdBy,
        LocalDateTime modifiedDate,
        String modifiedBy

) {
    public static UserAccountDto of(String userId, String userPassword, String email, String nickname) {
        return new UserAccountDto(userId, userPassword, email, nickname, null, null, null, null);
    }

    public static UserAccountDto of(String userId, String userPassword, String email, String nickname, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountDto(userId, userPassword, email, nickname,  createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getUserId(),
                entity.getUserPw(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getCreatedDate(),
                entity.getCreatedBy(),
                entity.getModifiedDate(),
                entity.getModifiedBy()
        );
    }

    public UserAccount toEntity(){
        return UserAccount.of(
                userId,
                userPw,
                email,
                nickname
        );
    }
}