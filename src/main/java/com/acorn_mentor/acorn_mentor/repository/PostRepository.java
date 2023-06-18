package com.acorn_mentor.acorn_mentor.repository;

import com.acorn_mentor.acorn_mentor.domain.Post;
import com.acorn_mentor.acorn_mentor.domain.QPost;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PostRepository extends
        JpaRepository<Post, Long>,
        QuerydslPredicateExecutor<Post>, // QueryDSL 에 탑재되어있는 기본 검색기능 지원, 부분 검색만 되고 Exact Match만 가능.
        QuerydslBinderCustomizer<QPost>  { // 그래서 Customizer 기능을 사용함.

    Page<Post> findByTitleContaining(String title, Pageable pageable);
    Page<Post> findByContentContaining(String content, Pageable pageable);
    Page<Post> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
    Page<Post> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);
    Page<Post> findByHashtag(String hashtag, Pageable pageable);


    @Override
    default void customize(QuerydslBindings bindings, QPost root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.hashtag, root.content, root.createdBy, root.createdDate);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdDate).first(DateTimeExpression::eq);
    }

}