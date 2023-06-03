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

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post {
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



    // NOT NULL
    @CreatedDate @Column(nullable = false) private LocalDateTime createdDate; // 생성일자
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 작성자 nickname
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedDate; // 수정일자
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; // 수정자 nickname

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
