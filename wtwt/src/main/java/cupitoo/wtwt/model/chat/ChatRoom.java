package cupitoo.wtwt.model.chat;

import cupitoo.wtwt.model.BaseTimeEntity;
import cupitoo.wtwt.model.post.Post;
import cupitoo.wtwt.model.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public ChatRoom(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
