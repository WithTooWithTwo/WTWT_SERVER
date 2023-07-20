package cupitoo.wtwt.controller;

import cupitoo.wtwt.dto.user.SignUpRes;
import cupitoo.wtwt.dto.user.SignUpReq;
import cupitoo.wtwt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public SignUpRes saveUser(@ModelAttribute SignUpReq request) throws IOException {
        return new SignUpRes(userService.join(request));
    }
}
