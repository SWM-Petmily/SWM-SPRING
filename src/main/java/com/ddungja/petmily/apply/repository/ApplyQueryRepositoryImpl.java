package com.ddungja.petmily.apply.repository;


import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.ddungja.petmily.apply.domain.QApply.apply;
import static com.ddungja.petmily.like.domain.QLike.like;
import static com.ddungja.petmily.post.domain.QPost.post;
import static com.ddungja.petmily.post.domain.QSubCategory.subCategory;

@RequiredArgsConstructor
public class ApplyQueryRepositoryImpl implements ApplyQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Apply> getApplyList(Long userId, ApprovalType approval, Pageable pageable) {

// 이전 코드
        //        List<Apply> content = jpaQueryFactory.selectFrom(apply)
//                .leftJoin(apply.post, post).fetchJoin()
//                .leftJoin(apply.post.subCategory, subCategory).fetchJoin()
//                .leftJoin(apply.post.like, like).fetchJoin()
//                .where(apply.user.id.eq(userId).and(eqApproval(approval)))
//                .limit(pageable.getPageSize())
//                .offset(pageable.getOffset())
//                .fetch();

        List<Long> applyId = jpaQueryFactory.select(apply.id)
                .from(apply)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        List<Apply> content = jpaQueryFactory.selectFrom(apply)
                .leftJoin(apply.post, post).fetchJoin()
                .leftJoin(apply.post.subCategory, subCategory).fetchJoin()
                .leftJoin(apply.post.like, like).fetchJoin()
                .where(apply.user.id.eq(userId).and(eqApproval(approval)), apply.id.in(applyId))
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(apply.count())
                .from(apply)
                .where(apply.user.id.eq(userId).and(eqApproval(approval)));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);

    }


    public BooleanExpression eqApproval(ApprovalType approvalType) {
        return approvalType == null ? null : apply.approval.eq(approvalType);
    }
}
