package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.group.Preference;
import cupitoo.wtwt.model.user.Gender;
import lombok.Data;

import java.util.Optional;

@Data
public class PreferenceDto {
    private Gender gender;
    private Integer minAge;
    private Integer maxAge;
    private Integer preferHeadCount;

    public PreferenceDto() {}
    PreferenceDto(Preference preference) {
        this();
        if(preference != null) {
            this.gender = preference.getGender();
            this.minAge = preference.getMinAge();
            this.maxAge = preference.getMaxAge();
            this.preferHeadCount = preference.getHeadCount();
        }
    }

}
