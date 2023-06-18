package com.acorn_mentor.acorn_mentor.service;

import com.acorn_mentor.acorn_mentor.domain.Post;
import com.acorn_mentor.acorn_mentor.domain.type.SearchType;
import com.acorn_mentor.acorn_mentor.dto.PostAndCommentsDto;
import com.acorn_mentor.acorn_mentor.dto.PostDto;
import com.acorn_mentor.acorn_mentor.dto.PostUpdateDto;
import com.acorn_mentor.acorn_mentor.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j // logging
public class PostService {

    private final PostRepository postRepository;

    // 게시글 검색 Service
    @Transactional(readOnly = true)
    public Page<PostDto> searchPosts(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {     // Search 칸이 비었거나 null 이라면
            return postRepository.findAll(pageable).map(PostDto::from);
        }

        return switch (searchType) {    // SearchType enum 각 검색
            case TITLE -> postRepository.findByTitleContaining(searchKeyword, pageable).map(PostDto::from);
            case CONTENT -> postRepository.findByContentContaining(searchKeyword, pageable).map(PostDto::from);
            case HASHTAG -> postRepository.findByHashtag("#" + searchKeyword, pageable).map(PostDto::from);
            case NICKNAME -> postRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(PostDto::from);
            case ID -> postRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(PostDto::from);
        };

    }
    // 게시글 조회
    @Transactional(readOnly = true)
    public PostAndCommentsDto getPost(Long postId) {
        return postRepository.findById(postId).map(PostAndCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - postId: " + postId));
    }

    // 게시글 생성
    public void savePost(PostDto postDto) {
        postRepository.save(postDto.toEntity());
    }

    // 게시글 수정
    public void updatePost(PostDto postDto) {
        try {
            Post post = postRepository.getReferenceById(postDto.id());
            if (postDto.title() != null) { post.setTitle(postDto.title()); }
            if (postDto.content() != null) { post.setContent(postDto.content()); }
            post.setHashtag(postDto.hashtag());
        } catch (EntityNotFoundException entityNotFoundException) {
            log.warn("게시글 수정 실패. 해당 게시글을 찾을 수 없습니다 - postDto: {}", postDto);
        }
    }
    
    // 게시글 삭제
    public void deletePost(long postId) {
        postRepository.deleteById(postId);
    }
}
