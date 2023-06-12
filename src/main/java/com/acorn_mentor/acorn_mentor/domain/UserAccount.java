package com.acorn_mentor.acorn_mentor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdDate"),
        @Index(columnList = "createdBy")
})

@Entity
public class UserAccount extends AuditingField{
    @Id
    @Column(length = 50)
    private String userId;
    @Setter @Column(nullable = false) private String userPw;

    @Setter @Column(nullable = false, length = 100) private String email;
    @Setter @Column(nullable = false, length = 100) private String nickname;

    public UserAccount() {
    }

    public UserAccount(String userId, String userPw, String email, String nickname) {
        this.userId = userId;
        this.userPw = userPw;
        this.email = email;
        this.nickname = nickname;
    }

    public static UserAccount of(String userId, String userPw, String email, String nickname)
    {
        return UserAccount.of(userId, userPw, email, nickname);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
