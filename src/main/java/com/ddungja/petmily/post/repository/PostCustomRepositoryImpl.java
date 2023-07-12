package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.post.Post;
import com.ddungja.petmily.post.domain.post.PostStatusType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.ddungja.petmily.like.domain.QLike.like;
import static com.ddungja.petmily.post.domain.QSubCategory.subCategory;
import static com.ddungja.petmily.post.domain.post.QPost.post;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<Post> getMypost(Long userId, PostStatusType postStatusType, Pageable pageable) {
        List<Post> content = jpaQueryFactory.selectFrom(post)
                .leftJoin(post.subCategory, subCategory).fetchJoin()
                .leftJoin(post.like, like).fetchJoin()
                .where(post.user.id.eq(userId).and(eqPostStatusType(postStatusType)))
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(post.count())
                .from(post)
                .leftJoin(post.subCategory, subCategory).fetchJoin()
                .leftJoin(post.like, like).fetchJoin()
                .where(post.user.id.eq(userId).and(eqPostStatusType(postStatusType)));
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression eqPostStatusType(PostStatusType postStatusType) {
        return postStatusType == null ? null : post.status.eq(postStatusType);
    }
}