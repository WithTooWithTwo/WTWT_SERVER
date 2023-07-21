package cupitoo.wtwt.service;

import cupitoo.wtwt.model.User.User;
import cupitoo.wtwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    /**
     * 로그인
     */
    public Long login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new IllegalStateException("존재하지 않는 이메일 입니다.");

        if (user.getPassword().equals(password)) return user.getId();
        else throw new IllegalStateException("잘못된 비밀번호 입니다.");
    }
}
