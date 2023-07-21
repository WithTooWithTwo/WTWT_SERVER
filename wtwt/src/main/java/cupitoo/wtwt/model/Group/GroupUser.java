package cupitoo.wtwt.model.Group;

import cupitoo.wtwt.model.Image;
import cupitoo.wtwt.model.User.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GroupUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_user_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //== 생성자 ==//
    public GroupUser(Group group, User user) {
        this.group = group;
        this.user = user;
    }
}
