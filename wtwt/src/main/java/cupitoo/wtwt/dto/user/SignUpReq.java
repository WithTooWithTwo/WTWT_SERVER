package cupitoo.wtwt.dto.user;

import cupitoo.wtwt.model.User.Gender;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode
public class SignUpReq {
    private String name;
    private String phoneNumber;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    private MultipartFile profileImage;
    @NotEmpty
    private String nickname;
    private String statusMessage;
    private Gender gender;
    private String bYear;
    private String bMonth;
    private String bDay;
}
