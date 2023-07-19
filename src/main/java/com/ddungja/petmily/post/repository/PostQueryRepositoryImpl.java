package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.ddungja.petmily.like.domain.QLike.like;
import static com.ddungja.petmily.post.domain.QPost.post;
import static com.ddungja.petmily.post.domain.QSubCategory.subCategory;

@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<Post> getMyPost(Long userId, PostStatusType postStatusType, Pageable pageable) {
        List<Long> postId = jpaQueryFactory.select(post.id)
                .from(post)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<Post> content = jpaQueryFactory.selectFrom(post)
                .leftJoin(post.subCategory, subCategory).fetchJoin()
                .leftJoin(post.like, like).fetchJoin()
                .where(post.user.id.eq(userId).and(eqPostStatusType(postStatusType)), post.id.in(postId))
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(post.count())
                .from(post)
                .where(post.user.id.eq(userId).and(eqPostStatusType(postStatusType)));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression eqPostStatusType(PostStatusType postStatusType) {
        return postStatusType == null ? null : post.status.eq(postStatusType);
    }
}
