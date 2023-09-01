package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.review.Review;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentReview {
    Float rate;
    String comment;
    UserProfile writer;
    LocalDate writeAt;

    public CommentReview(Review review) {
        this.rate = review.getRate();
        this.comment = review.getComment();
        this.writer = new UserProfile(review.getSender());
        this.writeAt = review.getCreatedAt().toLocalDate();
    }
}