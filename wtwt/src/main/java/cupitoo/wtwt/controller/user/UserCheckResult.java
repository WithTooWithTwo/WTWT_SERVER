package cupitoo.wtwt.controller.user;

import cupitoo.wtwt.dto.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCheckResult {
    Boolean isExist = false;
    UserProfile user;
}
