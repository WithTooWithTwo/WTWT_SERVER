package cupitoo.wtwt.service;

import cupitoo.wtwt.model.review.Personality;
import cupitoo.wtwt.model.review.Style;
import cupitoo.wtwt.repository.review.PersonalityRepository;
import cupitoo.wtwt.repository.review.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StyleService {
    private final StyleRepository repository;

    /**
     * 동행자 여행 스타일 타입 생성
     */
    @Transactional
    public Style save(String name) {
        return repository.save(new Style(name));
    }

    /**
     * 모든 동행자 여행 스타일 타입 조회!
     */
    public List<Style> findAll() {
        return repository.findAll();
    }
}
