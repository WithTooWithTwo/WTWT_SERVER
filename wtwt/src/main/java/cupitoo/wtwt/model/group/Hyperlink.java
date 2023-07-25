package cupitoo.wtwt.model.group;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hyperlink {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private Long id;
    private String link;
    private String name;

    public Hyperlink(String link, String name) {
        this.link = link;
        this.name = name;
    }
}
