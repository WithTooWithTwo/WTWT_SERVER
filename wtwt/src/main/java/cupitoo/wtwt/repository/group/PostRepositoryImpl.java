package cupitoo.wtwt.repository.group;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import cupitoo.wtwt.model.Category;
import cupitoo.wtwt.model.group.OrderOption;
import cupitoo.wtwt.model.post.Post;
import cupitoo.wtwt.model.user.Gender;
import cupitoo.wtwt.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static cupitoo.wtwt.model.QCategory.category;
import static cupitoo.wtwt.model.group.QGroup.group;
import static cupitoo.wtwt.model.post.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory query;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Post> findAllWithFilter(PostSearch postSearch) {
        // order category gender minAge maxAge lightning date minHeadCount maxHeadCount keyword
        // order : [RECENT, POPULAR, SOON]

        return query.select(post)
                .from(post)
                .join(post.group.category, category)
                .join(post.group, group)
                .where(categoryEq(categoryRepository.findByName(postSearch.getCategory())),
                        lightningEq(postSearch.getLightning()),
                        genderEq(postSearch.getGender()),
                        ageIn(postSearch.getMinAge(), postSearch.getMaxAge()),
                        dateIn(postSearch.getDate()),
                        haedCountIn(postSearch.getMinHeadCount(), postSearch.getMaxHeadCount()),
                        keywordLike(postSearch.getKeyword())) // 동적 쿼리 구현!
                .orderBy(order(postSearch.getOrder()))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression keywordLike(String keyword) {
        if(keyword == null) return null;
        StringBuffer sb = new StringBuffer();
        sb.append("%").append(keyword).append("%");
        String query = sb.toString();
        return group.post.title.like(query).or(group.post.text.like(query));
    }

    private BooleanExpression dateIn(LocalDate date) {
        if(date == null) return null;
        return group.firstDay.before(date).and(group.lastDay.after(date));
    }

    private BooleanExpression ageIn(Integer minAge, Integer maxAge) {
        if(minAge == null && maxAge == null) return null;
        if(minAge == null) {
            return group.preference.maxAge.loe(maxAge);
        }
        if(maxAge == null) {
            return group.preference.minAge.goe(minAge);
        }

        return group.preference.maxAge.loe(maxAge).and(group.preference.minAge.goe(minAge));
    }

    private BooleanExpression haedCountIn(Integer minHeadCount, Integer maxHeadCount) {
        if(minHeadCount == null && maxHeadCount == null) return null;
        if(minHeadCount == null) {
            return group.preference.headCount.loe(maxHeadCount);
        }
        if(maxHeadCount == null) {
            return group.preference.headCount.goe(minHeadCount);
        }

        return group.preference.headCount.between(minHeadCount, maxHeadCount);
    }

    private BooleanExpression genderEq(Gender gender) {
        if(gender == null) return null;
        return gender.equals(Gender.NONE)? Expressions.asBoolean(true).isTrue() : post.group.preference.gender.eq(gender);
    }

    private BooleanExpression lightningEq(Boolean lightning) {
        if (lightning == null) return null;
        return post.group.lightning.eq(lightning);
    }

    private OrderSpecifier<?> order(OrderOption option) {
        if(option == null) return post.createdAt.asc();

        switch (option) {
            case RECENT: return post.createdAt.asc();
            case POPULAR: return post.hits.desc();
            case SOON: return group.firstDay.asc();
        }
        return post.createdAt.asc();
    }

    private BooleanExpression categoryEq(Category category) {
        if (category == null) return null;
        return post.group.category.eq(category);
    }
}
