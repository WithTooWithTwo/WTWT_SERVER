package cupitoo.wtwt.service;

import cupitoo.wtwt.model.Category;
import cupitoo.wtwt.model.review.Style;
import cupitoo.wtwt.repository.CategoryRepository;
import cupitoo.wtwt.repository.review.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * 동행자 여행 스타일 타입 생성
     */
    @Transactional
    public Category save(String name) {
        return categoryRepository.save(new Category(name));
    }

    /**
     * 모든 동행자 여행 스타일 타입 조회!
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
