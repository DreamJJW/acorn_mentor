package com.acorn_mentor.acorn_mentor.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(PostController.class) // PostController 만 읽어들이고 테스트 실행.
class PostControllerTest {

    private final MockMvc mvc;

    public PostControllerTest(@Autowired MockMvc mvc) { this.mvc = mvc;}

//    @Disabled("테스트 구현 중")
    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void post_list_view() throws Exception {
        // given

        // when & then
        mvc.perform(MockMvcRequestBuilders.get("/posts/index"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("posts"));
    }

    @Disabled("테스트 구현 중")
    @DisplayName("[view][GET] 게시글 특정 페이지 - 정상 호출")
    @Test
    public void specific_post_view() throws Exception {
        // given

        // when & then
        mvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("posts/detail"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attributeExists("comments"));


    }
    @Disabled("테스트 구현 중")
    @DisplayName("[view][GET] 게시글 검색 전용 페이지 - 정상 호출")
    @Test
    public void post_search() throws Exception {
        // given

        // when & then
        mvc.perform(get("/posts/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
        
    }
    @Disabled("테스트 구현 중")
    @DisplayName("[view][GET] 게시글 해시태그 검색 페이지 - 정상 호출")
    @Test
    public void hashtag_search() throws Exception {
        // given

        // when & then
        mvc.perform(get("/posts/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));

    }
}