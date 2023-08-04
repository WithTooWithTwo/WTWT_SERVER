package cupitoo.wtwt.service;

import cupitoo.wtwt.controller.user.GroupListElementsForUserInfo;
import cupitoo.wtwt.controller.user.SignUpReq;
import cupitoo.wtwt.dto.UserDto;
import cupitoo.wtwt.dto.UserProfile;
import cupitoo.wtwt.model.user.User;
import cupitoo.wtwt.repository.UserRepository;
import cupitoo.wtwt.repository.group.GroupRepository;
import cupitoo.wtwt.repository.group.PostRepository;
import cupitoo.wtwt.repository.review.ReviewRepository;
import cupitoo.wtwt.util.FileStore;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final PostRepository postRepository;
    private final ReviewRepository reviewRepository;
    private final FileStore fileStore;

    /**
     * 회원 가입
     */
    @Transactional //기본은 readOnly = false
    public Long join(SignUpReq request) throws IOException {
        validateDuplicateUser(request); //중복 회원 검증;

        User.UserBuilder userBuilder = User.builder()
                .nickname(request.getNickname())
                .password(request.getPassword())
                .email(request.getEmail())
                .gender(request.getGender())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .statusMessage(request.getStatusMessage());

        log.debug("request.getBYear() = " + request.getBYear().getClass());
        if(request.getBYear() != null) {
            userBuilder.birthday(LocalDate.of(Integer.parseInt(request.getBYear()),
                    Integer.parseInt(request.getBMonth()),
                    Integer.parseInt(request.getBDay())
                    )
            );
        }

        log.debug("request.getProfileImage() = " + request.getProfileImage().getClass());
        if(request.getProfileImage() != null) {
            userBuilder.profileImage(fileStore.storeFile(request.getProfileImage()));
        }

        return userRepository.save(userBuilder.build()).getId();
    }

    private void validateDuplicateUser(SignUpReq request) {
        User findUser1 = userRepository.findByNickname(request.getNickname());
        if(findUser1 != null) throw new IllegalStateException("이미 존재하는 닉네임입니다.");

        User findUser2 = userRepository.findByEmail(request.getEmail());
        if(findUser2 != null) throw new IllegalStateException("이미 존재하는 이메일입니다.");

        if(request.getPhoneNumber() != null) {
            User findUser3 = userRepository.findByPhoneNumber(request.getPhoneNumber());
            if(findUser3 != null) throw new IllegalStateException("이미 존재하는 휴대폰 번호입니다.");
        }
    }

    public UserDto findOne(Long userId, @Nullable Boolean isMe) {
        User user = userRepository.findById(userId).get();
        UserDto.UserDtoBuilder userBuilder = UserDto.builder()
                .id(user.getId())
                .profileImage(new UserProfile(user))
                .nickname(user.getNickname())
                .rate(user.getRate())
                .statusMessage(user.getStatusMessage())
                .countsOfGroups(groupRepository.countGroupByUser(user))
                .countsOfPosts(postRepository.countPostByUser(user))
                .countsOfReviews(reviewRepository.countReviewByReceiver(user))
//                .styles(reviewRepository.findStylesByUser(user))
//                .personalities(reviewRepository.findPersonalitiesByUser(user))
                .comments(reviewRepository.findCommentsByUser(user));

        if(isMe == false) {
            userBuilder.myGroups(groupRepository.findGroupsByUser(user)
                    .stream()
                    .map(g -> new GroupListElementsForUserInfo(g)).toList());
        }

        return userBuilder.build();
    }
}
