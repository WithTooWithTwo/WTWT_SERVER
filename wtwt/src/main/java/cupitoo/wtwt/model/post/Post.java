package cupitoo.wtwt.model.post;

import cupitoo.wtwt.model.BaseTimeEntity;
import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.Image;
import cupitoo.wtwt.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User createdBy;
    @OneToMany(mappedBy = "post")
    private List<PostImage> images = new ArrayList<>();
    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    private Group group;
    @NotEmpty
    private String title;
    private String text;
    private Integer hits = 0; // 조회수

    //== 생성 메서드 ==//
    public Post(String title, String text, User createdBy) {
        this.title = title;
        this.text = text;
        this.createdBy = createdBy;
    }

    //== 수정 메서드 ==//
    public void modifyPost(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public void addImage(Image image) {
        this.images.add(new PostImage(this, image));
    }
    public void updateHit() {
        hits += 1;
    }

    public void setGroup(Group group) {
        this.group = group;
        group.setPost(this);
    }
}
