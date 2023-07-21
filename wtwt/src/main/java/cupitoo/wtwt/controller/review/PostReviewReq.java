package cupitoo.wtwt.controller.review;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostReviewReq {
    private List<ReviewDto> reviews;
}
