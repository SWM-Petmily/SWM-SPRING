package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.QMainCategory;
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
                .where(post.user.id.eq(userId).and(eqPostStatusType(postStatusType)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<Post> content = jpaQueryFactory.selectFrom(post)
                .join(post.subCategory, subCategory).fetchJoin()
                .leftJoin(post.like, like).fetchJoin()
                .where(post.user.id.eq(userId).and(eqPostStatusType(postStatusType)), post.id.in(postId))
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(post.count())
                .from(post)
                .where(post.user.id.eq(userId).and(eqPostStatusType(postStatusType)));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Post> getMainPosts(PostFilterRequest postFilterRequest, Pageable pageable) {
        List<Post> content = jpaQueryFactory.selectFrom(post)
                .leftJoin(post.subCategory, subCategory).fetchJoin()
                .leftJoin(post.mainCategory, QMainCategory.mainCategory)
                .where(eqRegion(postFilterRequest.getRegion())
                        .and(eqMainCategory(postFilterRequest.getMainCategory()))
                        .and(eqSubCategory(postFilterRequest.getSubCategory()))
                        .and(eqGenderType(postFilterRequest.getGenderType()))
                        .and(eqNeuteredType(postFilterRequest.getNeuteredType()))
                        .and(eqAgeBetween(postFilterRequest.getAgeFrom(), postFilterRequest.getAgeTo()))
                        .and(eqMoneyBetween(postFilterRequest.getMoneyFrom(), postFilterRequest.getMoneyTo())))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(post.count())
                .from(post)
                .leftJoin(post.subCategory, subCategory)
                .leftJoin(post.mainCategory, QMainCategory.mainCategory)
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
    public Page<Post> getMainPosts(Long userId, PostFilterRequest postFilterRequest, Pageable pageable) {
        List<Post> content = jpaQueryFactory.selectFrom(post)
                .leftJoin(post.like, like).fetchJoin()
                .leftJoin(post.subCategory, subCategory).fetchJoin()
                .leftJoin(post.mainCategory, QMainCategory.mainCategory)
                .where(eqRegion(postFilterRequest.getRegion())
                        .and(eqMainCategory(postFilterRequest.getMainCategory()))
                        .and(eqSubCategory(postFilterRequest.getSubCategory()))
                        .and(eqGenderType(postFilterRequest.getGenderType()))
                        .and(eqNeuteredType(postFilterRequest.getNeuteredType()))
                        .and(eqAgeBetween(postFilterRequest.getAgeFrom(), postFilterRequest.getAgeTo()))
                        .and(eqMoneyBetween(postFilterRequest.getMoneyFrom(), postFilterRequest.getMoneyTo())))
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
                        .and(eqMoneyBetween(postFilterRequest.getMoneyFrom(), postFilterRequest.getMoneyTo())));
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);    }

    private BooleanBuilder eqRegion(String region) {
        return StringUtils.hasText(region) ? new BooleanBuilder(post.region.contains(region)) : new BooleanBuilder();
    }

    private BooleanBuilder eqMainCategory(String mainCategory) {
        return StringUtils.hasText(mainCategory) ? new BooleanBuilder(post.mainCategory.name.eq(mainCategory)) : new BooleanBuilder();
    }

    private BooleanBuilder eqSubCategory(String subCategory) {
        return StringUtils.hasText(subCategory) ? new BooleanBuilder(post.subCategory.name.eq(subCategory)) : new BooleanBuilder();
    }

    private BooleanExpression  eqGenderType(GenderType genderType){
        return genderType == null ? null : post.gender.eq(genderType);
    }

    private BooleanExpression  eqNeuteredType(NeuteredType neuterType) {
        return neuterType == null ? null : post.neutered.eq(neuterType);
    }

    private BooleanBuilder eqAgeBetween(Integer start, Integer end) {
        return start != null && end != null ? new BooleanBuilder(post.age.between(start, end)) : new BooleanBuilder();
    }

    private BooleanBuilder eqMoneyBetween(Integer start, Integer end) {
        return start != null && end != null ? new BooleanBuilder(post.money.between(start, end)) : new BooleanBuilder();
    }

    private BooleanExpression eqPostStatusType(PostStatusType postStatusType) {
        return postStatusType == null ? null : post.status.eq(postStatusType);
    }
}
