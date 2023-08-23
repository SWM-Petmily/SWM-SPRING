package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.request.PostFilterRequest;
import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.NeuteredType;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.ddungja.petmily.post.domain.QMainCategory.mainCategory;
import static com.ddungja.petmily.post.domain.QPost.post;
import static com.ddungja.petmily.post.domain.QSubCategory.subCategory;

@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Post> getMyPost(Long userId, PostStatusType postStatusType, Pageable pageable) {
        List<Post> content = jpaQueryFactory.selectFrom(post)
                .join(post.subCategory, subCategory).fetchJoin()
                .where(post.user.id.eq(userId).and(eqPostStatusType(postStatusType)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(post.count())
                .from(post)
                .where(post.user.id.eq(userId).and(eqPostStatusType(postStatusType)));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Post> getMainPosts(PostFilterRequest postFilterRequest, Pageable pageable) {
        List<Post> content = jpaQueryFactory.selectFrom(post)
                .join(post.subCategory, subCategory).fetchJoin()
                .join(post.mainCategory, mainCategory).fetchJoin()
                .where(eqRegion(postFilterRequest.getRegion())
                        .and(eqMainCategory(postFilterRequest.getMainCategory()))
                        .and(eqSubCategory(postFilterRequest.getSubCategory()))
                        .and(eqGenderType(postFilterRequest.getGenderType()))
                        .and(eqNeuteredType(postFilterRequest.getNeuteredType()))
                        .and(eqAgeBetween(postFilterRequest.getAgeFrom(), postFilterRequest.getAgeTo()))
                        .and(eqMoneyBetween(postFilterRequest.getMoneyFrom(), postFilterRequest.getMoneyTo()))
                        .and(eqPostStatusType(PostStatusType.SAVE)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(post.count())
                .from(post)
                .join(post.subCategory, subCategory)
                .join(post.mainCategory, mainCategory)
                .where(eqRegion(postFilterRequest.getRegion())
                        .and(eqMainCategory(postFilterRequest.getMainCategory()))
                        .and(eqSubCategory(postFilterRequest.getSubCategory()))
                        .and(eqGenderType(postFilterRequest.getGenderType()))
                        .and(eqNeuteredType(postFilterRequest.getNeuteredType()))
                        .and(eqAgeBetween(postFilterRequest.getAgeFrom(), postFilterRequest.getAgeTo()))
                        .and(eqMoneyBetween(postFilterRequest.getMoneyFrom(), postFilterRequest.getMoneyTo())));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Post> getMainPosts(Long userId, PostFilterRequest postFilterRequest, List<Long> reportPostIds,  Pageable pageable) {
        List<Post> content = jpaQueryFactory.selectFrom(post)
                .join(post.subCategory, subCategory).fetchJoin()
                .join(post.mainCategory, mainCategory).fetchJoin()
                .where(eqRegion(postFilterRequest.getRegion())
                        .and(eqMainCategory(postFilterRequest.getMainCategory()))
                        .and(eqSubCategory(postFilterRequest.getSubCategory()))
                        .and(eqGenderType(postFilterRequest.getGenderType()))
                        .and(eqNeuteredType(postFilterRequest.getNeuteredType()))
                        .and(eqAgeBetween(postFilterRequest.getAgeFrom(), postFilterRequest.getAgeTo()))
                        .and(eqMoneyBetween(postFilterRequest.getMoneyFrom(), postFilterRequest.getMoneyTo()))
                        .and(eqPostStatusType(PostStatusType.SAVE))
                        .and(post.id.notIn(reportPostIds)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(post.count())
                .from(post)
                .where(eqRegion(postFilterRequest.getRegion())
                        .and(eqMainCategory(postFilterRequest.getMainCategory()))
                        .and(eqSubCategory(postFilterRequest.getSubCategory()))
                        .and(eqGenderType(postFilterRequest.getGenderType()))
                        .and(eqNeuteredType(postFilterRequest.getNeuteredType()))
                        .and(eqAgeBetween(postFilterRequest.getAgeFrom(), postFilterRequest.getAgeTo()))
                        .and(eqMoneyBetween(postFilterRequest.getMoneyFrom(), postFilterRequest.getMoneyTo()))
                        .and(eqPostStatusType(PostStatusType.SAVE))
                        .and(post.id.notIn(reportPostIds)));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanBuilder eqRegion(String region) {
        return StringUtils.hasText(region) ? new BooleanBuilder(post.region.contains(region)) : new BooleanBuilder();
    }

    private BooleanExpression eqMainCategory(String mainCategory) {
        return StringUtils.hasText(mainCategory) ? post.mainCategory.name.eq(mainCategory) : null;
    }

    private BooleanExpression eqSubCategory(String subCategory) {
        return StringUtils.hasText(subCategory) ? post.subCategory.name.eq(subCategory) : null;
    }

    private BooleanExpression eqGenderType(GenderType genderType) {
        return genderType == null ? null : post.gender.eq(genderType);
    }

    private BooleanExpression eqNeuteredType(NeuteredType neuterType) {
        return neuterType == null ? null : post.neutered.eq(neuterType);
    }

    private BooleanExpression eqAgeBetween(Integer start, Integer end) {
        return start != null && end != null ? post.age.between(start, end) : null;
    }

    private BooleanExpression eqMoneyBetween(Integer start, Integer end) {
        return start != null && end != null ? post.money.between(start, end) : null;
    }

    private BooleanExpression eqPostStatusType(PostStatusType postStatusType) {
        return postStatusType == null ? null : post.status.eq(postStatusType);
    }
}
