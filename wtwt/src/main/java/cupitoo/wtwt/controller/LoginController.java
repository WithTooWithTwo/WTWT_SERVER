package cupitoo.wtwt.controller;

import cupitoo.wtwt.config.SessionConst;
import cupitoo.wtwt.dto.login.LoginReq;
import cupitoo.wtwt.dto.login.LoginRes;
import cupitoo.wtwt.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public LoginRes login(HttpServletRequest servlet, @RequestBody LoginReq request) {

        Long loginUser = loginService.login(request.getEmail(), request.getPassword());

        //로그인 성공
        HttpSession session = servlet.getSession(); //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성!
        session.setAttribute(SessionConst.LOGIN_USER, loginUser); // Member말고 보면 id를 저장!

        return new LoginRes(loginUser);
    }
}
