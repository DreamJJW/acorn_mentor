package com.acorn_mentor.acorn_mentor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdDate"),
        @Index(columnList = "createdBy")
})

@Entity
public class Comment extends AuditingField{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 댓글 id

    @Setter @ManyToOne(optional = false) private Post post; // 매핑할 게시글 객체
    @Setter @ManyToOne(optional = false) private UserAccount userAccount; // 매핑할 유저 정보(ID)

    @Setter @Column(nullable = false, length = 500) private String content; // 댓글 내용

    protected Comment() {
    }

    private Comment(UserAccount userAccount, String content, Post post) {
        this.userAccount = userAccount;
        this.content = content;
        this.post = post;
    }

    public static Comment of(UserAccount userAccount, String content, Post post) {
        return new Comment(userAccount, content, post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
