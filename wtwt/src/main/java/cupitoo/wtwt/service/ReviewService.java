package cupitoo.wtwt.service;

import cupitoo.wtwt.controller.review.ReviewDto;
import cupitoo.wtwt.model.Group.Group;
import cupitoo.wtwt.model.Image;
import cupitoo.wtwt.model.User.User;
import cupitoo.wtwt.model.review.PersonalityReview;
import cupitoo.wtwt.model.review.Review;
import cupitoo.wtwt.model.review.ReviewImage;
import cupitoo.wtwt.model.review.StyleReview;
import cupitoo.wtwt.repository.*;
import cupitoo.wtwt.repository.review.*;
import cupitoo.wtwt.util.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ReviewRepository reviewRepository;
    private final PersonalityRepository personalityRepository;
    private final PersonalityReviewRepository personalityReviewRepository;
    private final StyleRepository styleRepository;
    private final StyleReviewRepository styleReviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final FileStore fileStore;

    /**
     * 리뷰 생성
     */
    @Transactional
    public Long createReview(Long sender, Long groupId, ReviewDto reviewDto) throws IOException, IllegalAccessException {
        User user = userRepository.findById(sender).get();
        Group group = groupRepository.findById(groupId).get();

        validatePermission(user, group);

        Review review = Review.builder()
                .rete(reviewDto.getRete())
                .receiver(userRepository.findById(reviewDto.getReceiverId()).get())
                .group(group)
                .comment(reviewDto.getComment().orElse(null))
                .build();

        reviewRepository.save(review);

        for (Long id : reviewDto.getPersonalities().orElse(new ArrayList<>())) {
            PersonalityReview pr = new PersonalityReview(review, personalityRepository.findById(id).get());
            personalityReviewRepository.save(pr);
        }

        for (Long id : reviewDto.getStyles().orElse(new ArrayList<>())) {
            StyleReview sr = new StyleReview(review, styleRepository.findById(id).get());
            styleReviewRepository.save(sr);
        }

        List<Image> images = fileStore.storeFiles(reviewDto.getImages().orElse(new ArrayList<>()));
        for (Image image : images) {
            ReviewImage ri = new ReviewImage(review, image);
            reviewImageRepository.save(ri);
        }

        return review.getId();
    }
    private void validatePermission(User user, Group group) throws IllegalAccessException {
        if(!groupRepository.existsUserInGroup(group, user)) {
            throw new IllegalAccessException("리뷰 권한이 없습니다.");
        }
    }
}
