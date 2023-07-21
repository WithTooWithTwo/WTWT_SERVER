package cupitoo.wtwt.model.review;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalityReview {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personality_review_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personality_id")
    private Personality personality;

    //== 생성자 ==//
    public PersonalityReview(Review review, Personality personality) {
        this.review = review;
        this.personality = personality;
    }
}
