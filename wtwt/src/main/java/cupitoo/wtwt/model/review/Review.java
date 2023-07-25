package cupitoo.wtwt.model.review;

import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;
    @Range(min = 0, max = 5) //별점은 0 - 5 사이의 정수
    private Integer rete;
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User receiver;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "group_id")
    private Group group;
//    @OneToMany(mappedBy = "review")
//    private List<PersonalityReview> personalities = new ArrayList<>();
//    @OneToMany(mappedBy = "style")
//    private List<StyleReview> styles = new ArrayList<>();

//    //== 연관관계 메서드 ==//
//    public void addPersonality(Personality personality) {
//        this.personalities.add(new PersonalityReview(this, personality));
//    }
//
//    public void addStyle(Style style) {
//        this.styles.add(new StyleReview(this, style));
//    }
}
