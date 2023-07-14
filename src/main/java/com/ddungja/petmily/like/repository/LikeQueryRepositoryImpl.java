package com.ddungja.petmily.like.repository;

import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class LikeQueryRepositoryImpl implements LikeQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Like> adfs(Long userId, PostStatusType postStatusType, Pageable pageable) {
        return null;
    }


//    @Override
//    public Page<Like> adfs(Long userId, PostStatusType postStatusType, Pageable pageable) {
//        List<Like> contents = jpaQueryFactory.selectFrom(QLike.like)
//                .leftJoin(QLike.like.post, post).fetchJoin()
//                .leftJoin(QLike.like.post.subCategory, subCategory).fetchJoin()
//                .leftJoin(QLike.like.post.like, QLike.!like).fetchJoin()
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .where(QLike.like.user.id.eq(userId).and(eqPostStatusType(postStatusType)))
//                .fetch();
//
//        JPAQuery<Long> countQuery = jpaQueryFactory
//                .select(QLike.like.count())
//                .from(QLike.like)
//                .leftJoin(QLike.like.post, post).fetchJoin()
//                .where(QLike.like.user.id.eq(userId).and(eqPostStatusType(postStatusType)));
//
//        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
//    }
//


}
