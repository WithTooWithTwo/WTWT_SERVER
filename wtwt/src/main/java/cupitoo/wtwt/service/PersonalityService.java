package cupitoo.wtwt.service;

import cupitoo.wtwt.model.review.Personality;
import cupitoo.wtwt.repository.review.PersonalityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalityService {
    private final PersonalityRepository repository;

    /**
     * 동행자 성격 타입 생성
     */
    @Transactional
    public Personality save(String name) {
        return repository.save(new Personality(name));
    }

    /**
     * 모든 동행자 성격 타입 조회!
     */
    public List<Personality> findAll() {
        return repository.findAll();
    }
}
