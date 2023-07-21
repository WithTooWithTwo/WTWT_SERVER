package cupitoo.wtwt.repository.review;

import cupitoo.wtwt.model.review.Review;
import cupitoo.wtwt.model.review.Style;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleRepository extends JpaRepository<Style, Long> {
}
