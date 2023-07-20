package cupitoo.wtwt.model.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @NotEmpty
    private String nickname;
    @NotEmpty
    private String password;
    private Float rate = 0f; //별점 평균
    private String status_message;
    private String name;
    private String phoneNumber;
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender; //성별 [MALE, FEMALE, HIDE]
    private LocalDate birthday;
}
