package com.ddungja.petmily.like.repository;

import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.like.domain.QLike;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.querydsl.core.types.Predicate;
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
public class LikeQueryRepositoryImpl implements LikeQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Like> findByUserIdAndPostStatus(Long userId, PostStatusType postStatusType, Pageable pageable) {
        QLike like2 = new QLike("like2");
        List<Long> likeId = jpaQueryFactory.select(like.id)
                .from(like)
                .where(like.user.id.eq(userId).and(eqPostStatusType(postStatusType)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<Like> contents = jpaQueryFactory.selectFrom(like)
                .join(like.post, post).fetchJoin()
                .join(like.post.subCategory, subCategory).fetchJoin()
                .leftJoin(like.post.like, like2).fetchJoin()
                .where(like.id.in(likeId))
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(like.count())
                .from(like)
                .where(like.user.id.eq(userId).and(eqPostStatusType(postStatusType)));

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
    }

    private Predicate eqPostStatusType(PostStatusType postStatusType) {
        return postStatusType == null ? null : post.status.eq(postStatusType);
    }


}
