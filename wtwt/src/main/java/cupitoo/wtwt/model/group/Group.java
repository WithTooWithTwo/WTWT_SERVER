package cupitoo.wtwt.model.group;

import cupitoo.wtwt.model.Category;
import cupitoo.wtwt.model.Image;
import cupitoo.wtwt.model.user.User;
import cupitoo.wtwt.model.post.Post;
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
    @ManyToOne(fetch = FetchType.LAZY)
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
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupNotice> notices = new ArrayList<>();
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupLink> links = new ArrayList<>();
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupMemo> memos = new ArrayList<>();

    //== 수정 메서드 ==//
    public void addMember(GroupUser member) {
        this.members.add(member);
    }
    public void removeMember(GroupUser member) {
        for(int i = 0; i < members.size() - 1; ++i) {
            GroupUser gu = members.get(i);
           if(gu == member) members.remove(i);
        }
    }
    public void addNotice(GroupNotice notice) {
        this.notices.add(notice);
    }
    public void removeNotice(GroupNotice gn) {
        for(int i = 0; i < notices.size() - 1; ++i) {
            GroupNotice notice = notices.get(i);
            if(gn == notice) notices.remove(i);
        }
    }
    public void addLink(GroupLink link) {
        this.links.add(link);
    }
    public void addMemo(GroupMemo memo) {
        this.memos.add(memo);
    }
    public void modifyLeader(User user) {
        this.leader = user;
    }
    public void closeRecruit() {
        this.status = RecruitStatus.CLOSED;
    }
    public void setPost(Post post) {
        this.post = post;
    }
    public void changeGroupImage(Image image) { this.groupImage = image; }
}
