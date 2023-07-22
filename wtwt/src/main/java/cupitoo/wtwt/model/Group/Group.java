package cupitoo.wtwt.model.Group;

import cupitoo.wtwt.model.Category;
import cupitoo.wtwt.model.Image;
import cupitoo.wtwt.model.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "trip_group")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Group {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;
    @OneToMany(mappedBy = "group")
    private List<GroupUser> members = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User leader;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Image groupImage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private String groupName; // default: 게시물 제목
    @Enumerated(EnumType.STRING)
    private RecruitStatus status = RecruitStatus.OPEN;
    private LocalDate firstDay;
    private LocalDate lastDay;
    private boolean lightning = false;
    @Embedded
    private Preference preference;

    //== 수정 메서드 ==//
    void addMember(User user) {
        this.members.add(new GroupUser(this, user));
    }
    void modifyLeader(User user) {
        this.leader = user;
    }
    void closeRecruit() {
        this.status = RecruitStatus.CLOSED;
    }

}
