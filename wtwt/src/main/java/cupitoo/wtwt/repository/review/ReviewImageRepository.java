package cupitoo.wtwt.repository.review;

import cupitoo.wtwt.model.review.Review;
import cupitoo.wtwt.model.review.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
