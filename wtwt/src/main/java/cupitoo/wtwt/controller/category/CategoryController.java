package cupitoo.wtwt.controller.category;

import cupitoo.wtwt.controller.PostResponse;
import cupitoo.wtwt.dto.CategoryDto;
import cupitoo.wtwt.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 카테고리 생성
     */
    @PostMapping
    public PostResponse addCategory(@ModelAttribute String name) {
        return new PostResponse(categoryService.save(name).getId());
    }

    /**
     * 카테고리 전체 조회
     */
    @GetMapping
    public CategoryList categories() {
        return new CategoryList(categoryService.findAll()
                .stream()
                .map(o -> new CategoryDto(o))
                .collect(toList()));
    }
}
