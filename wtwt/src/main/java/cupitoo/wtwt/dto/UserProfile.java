package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.User.User;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UserProfile {
    private Long id;
    private String nickname;
    private String profile;

    UserProfile(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        if (user.getProfileImage() != null) {
            this.profile = user.getProfileImage().getFileName();
        } else this.profile = null;
    }
}
