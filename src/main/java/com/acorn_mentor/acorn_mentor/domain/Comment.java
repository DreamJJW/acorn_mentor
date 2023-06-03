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
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdDate"),
        @Index(columnList = "createdBy")
})

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 댓글 id

    @Setter @Column(nullable = false, length = 500) private String content; // 댓글 내용
    @Setter @ManyToOne(optional = false) private Post post; // 게시글 id

    @CreatedDate @Column(nullable = false) private LocalDateTime createdDate; // 생성일자
    @CreatedBy @Column(nullable = false)private String createdBy; // 작성자 nickname
    @LastModifiedDate @Column(nullable = false)private LocalDateTime modifiedDate; // 수정일자
    @LastModifiedBy @Column(nullable = false)private String modifiedBy; // 수정자 nickname

    protected Comment() {
    }

    private Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }

    public static Comment of(String content, Post post) {
        return new Comment(content, post);
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
