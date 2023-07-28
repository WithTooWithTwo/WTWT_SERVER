package cupitoo.wtwt.controller.user;

import cupitoo.wtwt.annotation.Login;
import cupitoo.wtwt.dto.UserDto;
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

    /**
     * 마이페이지 정보
     */
    @GetMapping
    public UserDto findMe(@Login Long userId) throws IOException {
        return userService.findOne(userId, true);
    }

    @GetMapping("/{id}")
    public UserDto findUser(@PathVariable("id") Long userId) throws IOException {
        return userService.findOne(userId, false);
    }
}
