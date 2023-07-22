package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.Group.Preference;
import cupitoo.wtwt.model.User.Gender;
import lombok.Data;

@Data
public class PreferenceDto {
    private Gender gender;
    private int minAge;
    private int maxAge;
    private int preferHeadCount;

    PreferenceDto(Preference preference) {
        this.gender = preference.getGender();
        this.minAge = preference.getMinAge();
        this.maxAge = preference.getMaxAge();
        this.preferHeadCount = preference.getHeadCount();
    }

}
