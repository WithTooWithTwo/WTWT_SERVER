package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.group.Preference;
import cupitoo.wtwt.model.user.Gender;
import lombok.Data;

@Data
public class PreferenceDto {
    private Gender gender;
    private int minAge;
    private int maxAge;
    private int preferHeadCount;

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