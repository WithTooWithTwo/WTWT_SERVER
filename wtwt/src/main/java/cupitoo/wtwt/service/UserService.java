package cupitoo.wtwt.service;

import cupitoo.wtwt.dto.user.SignUpReq;
import cupitoo.wtwt.model.Image;
import cupitoo.wtwt.model.User.User;
import cupitoo.wtwt.repository.UserRepository;
import cupitoo.wtwt.util.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
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
                .status_message(request.getStatusMessage());

        if(request.getBYear() != null) {
            userBuilder.birthday(LocalDate.of(Integer.parseInt(request.getBYear()),
                    Integer.parseInt(request.getBMonth()),
                    Integer.parseInt(request.getBDay())
                    )
            );
        }

        if(request.getProfileImage() != null) {
            userBuilder.profileImage(fileStore.storeFile(request.getProfileImage()));
        }

        return userRepository.save(userBuilder.build()).getId();
    }

    private void validateDuplicateUser(SignUpReq request) {
        User findUser1 = userRepository.findByNickname(request.getNickname());
        if(findUser1 != null) throw new IllegalStateException("이미 존재하는 이메일입니다.");

        if(request.getEmail() != null) {
            User findUser2 = userRepository.findByNickname(request.getEmail());
            if(findUser2 != null) throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        if(request.getPhoneNumber() != null) {
            User findUser3 = userRepository.findByPhoneNumber(request.getPhoneNumber());
            if(findUser3 != null) throw new IllegalStateException("이미 존재하는 휴대폰 번호입니다.");
        }
    }
}
