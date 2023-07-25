package cupitoo.wtwt.repository.group;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import cupitoo.wtwt.model.Category;
import cupitoo.wtwt.model.group.OrderOption;
import cupitoo.wtwt.model.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cupitoo.wtwt.model.QCategory.category;
import static cupitoo.wtwt.model.group.QGroup.group;
import static cupitoo.wtwt.model.post.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public List<Post> findAllWithFilter(PostSearch postSearch) {
        return query.select(post)
                .from(post)
                .join(post.group.category, category)
                .join(post.group, group)
                .where(categoryEq(postSearch.getCategory())) // 동적 쿼리 구현!
                .where(lightningEq(postSearch.getLightning()))
                .orderBy(order(postSearch.getOrder()))
                .limit(1000)
                .fetch();
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
