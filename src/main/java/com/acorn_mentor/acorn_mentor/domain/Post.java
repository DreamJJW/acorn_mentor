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
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdDate"),
        @Index(columnList = "createdBy"),
})

@Entity
public class Post extends AuditingField{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 게시글 id
    
    @Setter @Column(nullable = false) private String title; // 게시글 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 게시글 본문

    @Setter private String hashtag; // 해시태그

    @ToString.Exclude
    @OrderBy("id")
    // Cascading
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    // 게시글의 댓글에는 중복을 허락하지 않고 콜렉션으로 본다.
    private final Set<Comment> comments = new LinkedHashSet<>();

    protected Post() {}

    public Post(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Post of(String title, String content, String hashtag) {
        return new Post(title, content, hashtag);
    }


}
