package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.Category;
import cupitoo.wtwt.model.group.OrderOption;
import cupitoo.wtwt.model.group.Preference;
import cupitoo.wtwt.model.user.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PostSearch {
    private OrderOption order; //정렬 옵션 [RECENT, POPULAR, SOON]
    private String category; // 카테고리
    private Gender gender;
    private Integer minAge;
    private Integer maxAge;
    private Boolean lightning;
    private LocalDate date; //여행 날짜
    private Integer minHeadCount;
    private Integer maxHeadCount;
    private String keyword; // 키워드
}
