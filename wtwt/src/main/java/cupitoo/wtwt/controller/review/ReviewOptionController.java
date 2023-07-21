package cupitoo.wtwt.controller.review;

import cupitoo.wtwt.controller.PostResponse;
import cupitoo.wtwt.service.PersonalityService;
import cupitoo.wtwt.service.StyleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/review-options")
public class ReviewOptionController {
    private final PersonalityService personalityService;
    private final StyleService styleService;

    /**
     * 동행자 성격 추가 생성
     */
    @PostMapping("/personalities")
    public PostResponse addPersonality(@ModelAttribute String name) {
        return new PostResponse(personalityService.save(name).getId());
    }

    /**
     * 동행자 여행 스타일 추가 생성
     */
    @PostMapping("/styles")
    public PostResponse addStyle(@ModelAttribute String name) {
        return new PostResponse(styleService.save(name).getId());
    }

    /**
     * 동행자 성격 리스트 조회
     */
    @GetMapping("/personalities")
    public ReviewOptionList findPersonalities() {
        return new ReviewOptionList(
                personalityService.findAll()
                        .stream()
                        .map(p -> new OptionDto(p))
                        .toList()
        );
    }

    /**
     * 동행자 여행 스타일 리스트 조회
     */
    @GetMapping("/styles")
    public ReviewOptionList findStyles() {
        return new ReviewOptionList(
                styleService.findAll()
                        .stream()
                        .map(p -> new OptionDto(p))
                        .toList()
        );
    }
}
