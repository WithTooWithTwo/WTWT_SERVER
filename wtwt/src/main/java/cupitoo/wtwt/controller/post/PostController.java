package cupitoo.wtwt.controller.post;

import cupitoo.wtwt.annotation.Login;
import cupitoo.wtwt.controller.PostResponse;
import cupitoo.wtwt.dto.PostDetails;
import cupitoo.wtwt.dto.PostListElement;
import cupitoo.wtwt.dto.PreferenceDto;
import cupitoo.wtwt.model.Category;
import cupitoo.wtwt.model.group.OrderOption;
import cupitoo.wtwt.model.group.Preference;
import cupitoo.wtwt.repository.group.PostSearch;
import cupitoo.wtwt.service.CategoryService;
import cupitoo.wtwt.service.PostService;
import cupitoo.wtwt.service.UserService;
import cupitoo.wtwt.service.S3Service;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final S3Service s3Service;

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
    @GetMapping
    public List<PostListElement> findPosts(@ModelAttribute PostSearch postSearch) {
        return postService.findAllWithFilter(postSearch);
    }

    /**
     * 단일 게시글 조회
     */
    @GetMapping("/{id}")
    public PostDetails findPost(@PathVariable("id") Long id) {
        PostDetails postDetails = postService.findPostWithGroup(id);
        return postDetails;
    }
}