package cupitoo.wtwt.repository.review;

import cupitoo.wtwt.model.user.User;
import cupitoo.wtwt.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT AVG(r.rete) FROM Review r WHERE r.receiver = :user")
    Float getAverageRateByReceiver(@Param("user") User user);
}