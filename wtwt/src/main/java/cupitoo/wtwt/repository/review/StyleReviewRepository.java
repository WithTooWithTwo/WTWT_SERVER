package cupitoo.wtwt.repository.review;

import cupitoo.wtwt.model.review.Review;
import cupitoo.wtwt.model.review.Style;
import cupitoo.wtwt.model.review.StyleReview;
import cupitoo.wtwt.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StyleReviewRepository extends JpaRepository<StyleReview, Long> {
}
