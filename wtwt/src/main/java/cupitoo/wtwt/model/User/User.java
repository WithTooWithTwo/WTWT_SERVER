package cupitoo.wtwt.model.User;

import cupitoo.wtwt.model.Image;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image profileImage;
    @NotEmpty
    private String nickname;
    @NotEmpty
    private String password;
    @Email
    @NotEmpty
    private String email;
    private Float rate = 0f; //별점 평균
    private String status_message;
    private String name;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender; //성별 [MALE, FEMALE, HIDE]
    private LocalDate birthday;

    //== 수정 메서드 ==//
    public void updateRate(Float rate) {
        this.rate = rate;
    }
}
