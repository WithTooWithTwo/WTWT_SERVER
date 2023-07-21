package cupitoo.wtwt.controller.review;

import cupitoo.wtwt.annotation.Login;
import cupitoo.wtwt.controller.ErrorResponse;
import cupitoo.wtwt.service.PersonalityService;
import cupitoo.wtwt.service.ReviewService;
import cupitoo.wtwt.service.StyleService;
import cupitoo.wtwt.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;


    /**
     * 그룹에서 리뷰하기 -> 리뷰 당한 사람의 rete update!
     */
    @PostMapping("/{groupId}")
    public PostReviewRes postReviews(@Login Long sender,
                                  @PathVariable Long groupId,
                                  @ModelAttribute PostReviewReq request) throws IOException, IllegalAccessException {

        List<Long> result = new ArrayList<>();
        for (ReviewDto reviewDto : request.getReviews()) {
            result.add(reviewService.createReview(sender, groupId, reviewDto));
        }

        return new PostReviewRes(result);
    }


    /**
     * 리뷰 보기 -> 별점
     */
    

    /**
     * 예외 핸들러
     */
    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIllegalAccessException(IllegalAccessException ex) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
