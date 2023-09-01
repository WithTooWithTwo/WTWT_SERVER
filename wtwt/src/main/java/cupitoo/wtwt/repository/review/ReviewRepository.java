package cupitoo.wtwt.repository.review;

import cupitoo.wtwt.dto.CommentReview;
import cupitoo.wtwt.model.user.User;
import cupitoo.wtwt.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    @Query("SELECT AVG(r.rate) FROM Review r WHERE r.receiver = :user")
    Float getAverageRateByReceiver(@Param("user") User user);

    int countReviewByReceiver(User user);

    @Query("SELECT r FROM Review r WHERE r.receiver = :user and r.comment != ''")
    List<Review> findAllReviewWithCommentByReceiver(@Param("user") User user);
}