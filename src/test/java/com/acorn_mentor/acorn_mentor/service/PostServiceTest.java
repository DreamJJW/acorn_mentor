package com.acorn_mentor.acorn_mentor.service;

import com.acorn_mentor.acorn_mentor.domain.Post;
import com.acorn_mentor.acorn_mentor.domain.type.SearchType;
import com.acorn_mentor.acorn_mentor.dto.PostDto;
import com.acorn_mentor.acorn_mentor.dto.PostUpdateDto;
import com.acorn_mentor.acorn_mentor.repository.PostRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

//@Disabled
//@ExtendWith(MockitoExtension.class)
//class PostServiceTest {
//
//    /*
//    검색
//    각 게시글 페이지로 이동
//    페이지네이션
//    홈 버튼 -> 리다이렉션
//    정렬 기능
//     */
//
//    @InjectMocks private PostService postService;
//    @Mock private PostRepository postRepository;
//
//
//    @Test
//    @DisplayName("게시글 검색")
//    void post_search() {
//
//        // given
//        SearchType searchType = SearchType.TITLE;
//        String searchKeyword = "title";
//        Pageable pageable = Pageable.ofSize(20);
//        given(postRepository.findByTitleContaining(searchKeyword, pageable)).willReturn(Page.empty());
//
//        // when
//        Page<PostDto> postDtoList = postService.searchPosts(searchType, searchKeyword, pageable); // 제목, 본문, 작성자_id, 닉네임, 해시태그
//
//        // then
//        assertThat(postDtoList).isEmpty();
//        then(postRepository).should().findByTitleContaining(searchKeyword, pageable);
//    }
//
//    @Test
//    @DisplayName("게시글 조회")
//    void post_view() {
//        // Given
//        PostDto postDto = postService.searchPost(1L);
//        //Then
//        assertThat(postDto).isNotNull();
//    }
//
//    @Test
//    @DisplayName("게시글 작성")
//    void new_post() {
//        // Given
//        given(postRepository.save(ArgumentMatchers.any(Post.class))).willReturn(null);
//        // When
//        postService.savePost(PostDto.of(LocalDateTime.now(), "Jiwoo", "title", "content", "hashtag"));
//        // Then
//        BDDMockito.then(postRepository).should().save(ArgumentMatchers.any(Post.class));
//    }
//
//    @Test
//    @DisplayName("게시글 수정")
//    void update_post() {
//        // Given
//        given(postRepository.save(ArgumentMatchers.any(Post.class))).willReturn(null);
//        // When
//        postService.updatePost(1L, PostUpdateDto.of("title", "content", "#java"));
//        // Then
//        BDDMockito.then(postRepository).should().save(ArgumentMatchers.any(Post.class));
//    }
//
//    @Test
//    @DisplayName("게시글 삭제")
//    void delete_post() {
//        // Given
//        willDoNothing().given(postRepository).delete(ArgumentMatchers.any(Post.class));
//        // When
//        postService.deletePost(1L);
//        // Then
//        BDDMockito.then(postRepository).should().delete(ArgumentMatchers.any(Post.class));
//    }
//
//
//
//}