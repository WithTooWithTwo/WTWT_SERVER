package cupitoo.wtwt.model.review;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StyleReview {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "style_review_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id")
    private Style style;

    //== 생성자 ==//
    public StyleReview(Review review, Style style) {
        this.review = review;
        this.style = style;
    }
}
