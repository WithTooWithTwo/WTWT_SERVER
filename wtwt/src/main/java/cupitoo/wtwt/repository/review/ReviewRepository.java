package cupitoo.wtwt.repository.review;

import cupitoo.wtwt.model.user.User;
import cupitoo.wtwt.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT AVG(r.rete) FROM Review r WHERE r.receiver = :user")
    Float getAverageRateByReceiver(@Param("user") User user);

    int countReviewByReceiver(User user);

//    @Query("SELECT DISTINCT s.type FROM Style s JOIN FETCH StyleReview sr, Review r" +
//            "WHERE r.receiver = :user AND sr.review = r")
//    List<String> findStylesByUser(@Param("user") User user);
//
//    @Query("SELECT DISTINCT p.type FROM Personality p JOIN FETCH PersonalityReview pr , Review r WHERE r.receiver = :user AND pr.review = r")
//    List<String> findPersonalitiesByUser(@Param("user") User user);

    @Query("SELECT r.comment FROM Review r WHERE r.receiver = :user")
    List<String> findCommentsByUser(@Param("user") User user);
}