package cupitoo.wtwt.repository.review;

import cupitoo.wtwt.model.review.Personality;
import cupitoo.wtwt.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityRepository extends JpaRepository<Personality, Long> {
}
