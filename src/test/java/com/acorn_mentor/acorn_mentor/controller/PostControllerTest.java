package com.acorn_mentor.acorn_mentor.controller;

import com.acorn_mentor.acorn_mentor.config.SecurityConfig;
import com.acorn_mentor.acorn_mentor.dto.PostAndCommentsDto;
import com.acorn_mentor.acorn_mentor.dto.UserAccountDto;
import com.acorn_mentor.acorn_mentor.service.PostService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@Import(SecurityConfig.class)
@WebMvcTest(PostController.class) // PostController 만 읽어들이고 테스트 실행.
class PostControllerTest {

    private final MockMvc mvc;

    @MockBean private PostService postService;

    public PostControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

//  @Disabled("테스트 구현 중")
    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void post_list_view() throws Exception {
        // given
        BDDMockito.given(postService.searchPosts(ArgumentMatchers.eq(null),
                ArgumentMatchers.eq(null), ArgumentMatchers.any(Pageable.class)))
                        .willReturn(Page.empty());

        // when & then
        mvc.perform(MockMvcRequestBuilders.get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("posts/index"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attributeExists("searchTypes"));
        BDDMockito.then(postService).should().searchPosts(ArgumentMatchers.eq(null),
                ArgumentMatchers.eq(null), ArgumentMatchers.any(Pageable.class));
    }

    @DisplayName("[view][GET] 게시글 특정 페이지 - 정상 호출")
    @Test
    public void specific_post_view() throws Exception {
        // given
        Long postId = 1L;
        BDDMockito.given(postService.getPost(postId)).willReturn(createPostAndCommentDto());
        // when & then
        mvc.perform(get("/posts/" + postId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("posts/detail"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attributeExists("comments"));
        BDDMockito.then(postService).should().getPost(postId);

    }
//    @Disabled("테스트 구현 중")
//    @DisplayName("[view][GET] 게시글 검색 전용 페이지 - 정상 호출")
//    @Test
//    public void post_search() throws Exception {
//        // given
//
//        // when & then
//        mvc.perform(get("/posts/search"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
//
//    }
//    @Disabled("테스트 구현 중")
//    @DisplayName("[view][GET] 게시글 해시태그 검색 페이지 - 정상 호출")
//    @Test
//    public void hashtag_search() throws Exception {
//        // given
//
//        // when & then
//        mvc.perform(get("/posts/search-hashtag"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
//
//    }

    // 테스트용 데이터
    private PostAndCommentsDto createPostAndCommentDto(){
        return PostAndCommentsDto.of(
                1L,
                createUserAccountDto(),
                Set.of(),
                "title",
                "content",
                "#hashtag",
                LocalDateTime.now(),
                "jiwoo",
                LocalDateTime.now(),
                "jiwoo"
        );
    }

    // 테스트용 데이터
    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "jiwoo",
                "1234",
                "jiwoo@naver.com",
                "jiwoo",
                LocalDateTime.now(),
                "jiwoo",
                LocalDateTime.now(),
                "jiwoo"
        );
    }
}