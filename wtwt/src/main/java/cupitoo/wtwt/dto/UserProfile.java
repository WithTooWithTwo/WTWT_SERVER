package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.User.User;
import lombok.Data;

@Data
public class UserProfile {
    private Long id;
    private String nickname;
    private String profile;

    UserProfile(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.profile = user.getProfileImage().getFileName();
    }
}
