package cupitoo.wtwt.model.Group;

import cupitoo.wtwt.model.Image;
import cupitoo.wtwt.model.User.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
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
