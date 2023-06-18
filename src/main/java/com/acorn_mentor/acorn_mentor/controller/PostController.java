package com.acorn_mentor.acorn_mentor.controller;

import com.acorn_mentor.acorn_mentor.domain.type.SearchType;
import com.acorn_mentor.acorn_mentor.dto.response.PostAndCommentsResponse;
import com.acorn_mentor.acorn_mentor.dto.response.PostResponse;
import com.acorn_mentor.acorn_mentor.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping
    public String posts(
        @RequestParam(required = false) SearchType searchType,
        @RequestParam(required = false) String searchValue,
        @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable,
        ModelMap modelMap
    ) {
        modelMap.addAttribute("posts", postService.searchPosts(searchType, searchValue, pageable)
                .map(PostResponse::from)); // searchType model 찾을 수 없는 오류 발생
        return "posts/index";
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable Long postId, ModelMap modelMap) {
        PostAndCommentsResponse postAndCommentsResponse = PostAndCommentsResponse.from(postService.getPost(postId));
        modelMap.addAttribute("post", postAndCommentsResponse);
        modelMap.addAttribute("comments", postAndCommentsResponse.commentResponses());
        return "posts/detail";
    }


}
