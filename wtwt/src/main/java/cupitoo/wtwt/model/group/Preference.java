package cupitoo.wtwt.model.group;

import cupitoo.wtwt.model.user.Gender;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
public class Preference {
    @Enumerated(EnumType.STRING)
    private Gender gender; //성별 [MALE, FEMALE, NONE]
    private int headCount;
    private int minAge;
    private int maxAge;
}
