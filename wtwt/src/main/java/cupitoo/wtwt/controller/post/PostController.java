package cupitoo.wtwt.controller;

import cupitoo.wtwt.repository.PostRepository;
import cupitoo.wtwt.service.PostService;
import cupitoo.wtwt.service.UserService;
import cupitoo.wtwt.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final FileStore fileStore;

}
