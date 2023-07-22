package cupitoo.wtwt.controller.post;

import cupitoo.wtwt.annotation.Login;
import cupitoo.wtwt.controller.PostResponse;
import cupitoo.wtwt.dto.PostDetails;
import cupitoo.wtwt.model.Group.Post;
import cupitoo.wtwt.service.PostService;
import cupitoo.wtwt.service.UserService;
import cupitoo.wtwt.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final FileStore fileStore;

    /**
     * 게시글 작성 -> 그룹 자동 생성
     */
    @PostMapping
    public PostResponse savePost(@Login Long userId, @ModelAttribute CreatePostReq request) throws IOException {
        return new PostResponse(postService.save(userId, request));
    }

    /**
     * 게시글 리스트 조회
     */

    /**
     * 단일 게시글 조회
     */
    @GetMapping("/{id}")
    public PostDetails findPost(@PathVariable("id") Long id) {
        PostDetails postDetails = postService.findPostWithGroup(id);
        return postDetails;
    }
}
