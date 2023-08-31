package cupitoo.wtwt.model.group;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_id")
    private Long id;
    private String contents;

    public Memo(String contents) {
        this.contents = contents;
    }

    public void changeContents(String contents) { this.contents = contents; }
}
