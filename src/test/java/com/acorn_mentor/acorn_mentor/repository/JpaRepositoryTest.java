package com.acorn_mentor.acorn_mentor.repository;

import com.acorn_mentor.acorn_mentor.config.JPAConfig;
import com.acorn_mentor.acorn_mentor.domain.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("testdb")
@DisplayName("JPA 연결 테스트")
@Import(JPAConfig.class)
@DataJpaTest
class JpaRepositoryTest {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public JpaRepositoryTest(@Autowired PostRepository postRepository, @Autowired CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @DisplayName("select test")
    @Test
    void selectTest(){
        // Given

        // When
        List<Post> posts = postRepository.findAll();
        // Then
        assertThat(posts).isNotNull().hasSize(11);

    }

    @DisplayName("insert test")
    @Test
    void insertTest() {
        // Given
        long prevCnt = postRepository.count();

        // When
        Post savedPost = postRepository.save(Post.of("new post", "new content", "#spring"));

        // Then
        assertThat(postRepository.count()).isEqualTo(prevCnt + 1);

    }

    @DisplayName("update test")
    @Test
    void updateTest() {
        // Given
        Post post = postRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        post.setHashtag(updatedHashtag);
        // When
        Post savedPost = postRepository.save(post);

        // Then
        assertThat(savedPost).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);

    }

    @DisplayName("delete test")
    @Test
    void deleteTest() {
        // Given
        Post post = postRepository.findById(1L).orElseThrow();
        long prevPostCnt = postRepository.count(); // 기존 글 개수
        long prevCommentCnt = commentRepository.count(); // 기존 댓글 개수
        // cascading 옵션으로 글 <-> 댓글을 매핑했기 때문에 글을 delete 하게 된다면, 그 글에 있는 댓글들도 함께 delete 하는 것을
        // 고려해야 한다.
        int deletedCommentSize = post.getComments().size();

        // When
        postRepository.delete(post);

        // Then
        assertThat(postRepository.count()).isEqualTo(prevPostCnt - 1);
        assertThat(commentRepository.count()).isEqualTo(prevCommentCnt - deletedCommentSize);

    }
}