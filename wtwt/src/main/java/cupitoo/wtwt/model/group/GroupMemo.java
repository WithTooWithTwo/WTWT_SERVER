package cupitoo.wtwt.model.group;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupMemo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_memo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "memo_id")
    private Memo memo;

    public GroupMemo(Group group, Memo memo) {
        this.group = group;
        group.addMemo(this);
        this.memo = memo;
    }
}
