package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.user.User;
import lombok.Data;

@Data
public class UserProfile {
    private Long id;
    private String nickname;
    private String profile;

    public UserProfile(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        if (user.getProfileImage() != null) {
            this.profile = user.getProfileImage().getFileName();
        } else this.profile = null;
    }
}
