package com.acorn_mentor.acorn_mentor.domain;

import java.time.LocalDateTime;

public class Comment {
    private Long id; // 댓글 id
    private String content; // 댓글 내용
    private Post post; // 게시글 id

    private LocalDateTime createdDate; // 생성일자
    private String createdBy; // 작성자 nickname
    private LocalDateTime modifiedDate; // 수정일자
    private String modifiedBy; // 수정자 nickname
}
