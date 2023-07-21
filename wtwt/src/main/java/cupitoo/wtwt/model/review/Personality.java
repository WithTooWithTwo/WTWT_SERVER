package cupitoo.wtwt.model.review;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Personality {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personality_id")
    private Long id;
    String type;

    //== 생성 메서드 ==//
    public Personality(String type) {
        this.type = type;
    }
}
