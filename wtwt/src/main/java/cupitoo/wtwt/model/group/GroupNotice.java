package cupitoo.wtwt.model.group;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupNotice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_notice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motice_id")
    private Notice notice;

    public GroupNotice(Group group, Notice notice) {
        this.group = group;
        group.addNotice(this);
        this.notice = notice;
    }
}
