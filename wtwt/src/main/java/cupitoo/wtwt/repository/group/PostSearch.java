package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.Category;
import cupitoo.wtwt.model.Group.OrderOption;
import cupitoo.wtwt.model.Group.Preference;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostSearch {
    private OrderOption option; //정렬 옵션 [RECENT, POPULAR, SOON]
    private Category category; // 카테고리
    private Preference preference; //선호 동행인 Gender, minAge, maxAge
    private Boolean lightning;
    private LocalDate date; //여행 날짜
    private String keyword; // 키워드
}
