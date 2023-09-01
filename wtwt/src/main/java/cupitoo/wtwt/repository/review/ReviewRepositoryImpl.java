package cupitoo.wtwt.repository.review;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import cupitoo.wtwt.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cupitoo.wtwt.model.review.QPersonality.personality;
import static cupitoo.wtwt.model.review.QPersonalityReview.personalityReview;
import static cupitoo.wtwt.model.review.QReview.review;
import static cupitoo.wtwt.model.review.QStyle.style;
import static cupitoo.wtwt.model.review.QStyleReview.styleReview;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public List<Tuple> getUserStyles(User user) {
        return query.select(styleReview.style.type, styleReview.style.type.count())
                .from(styleReview)
                .join(styleReview.style, style)
                .join(styleReview.review, review)
                .where(styleReview.review.receiver.eq(user))
                .groupBy(styleReview.style.type)
                .orderBy(styleReview.style.type.count().desc())
                .fetch();
    }

    @Override
    public List<Tuple> getUserPersonality(User user) {
        return query.select(personalityReview.personality.type,
                        personalityReview.personality.type.count())
                .from(personalityReview)
                .join(personalityReview.personality, personality)
                .join(personalityReview.review, review)
                .where(personalityReview.review.receiver.eq(user))
                .groupBy(personalityReview.personality.type)
                .orderBy(personalityReview.personality.type.count().desc())
                .fetch();
    }
}
