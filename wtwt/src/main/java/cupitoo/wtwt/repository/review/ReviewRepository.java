package cupitoo.wtwt.repository.review;

import cupitoo.wtwt.model.User.User;
import cupitoo.wtwt.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT AVG(r.rete) FROM Review r WHERE r.receiver = :user")
    Float getAverageRateByReceiver(User user);
}