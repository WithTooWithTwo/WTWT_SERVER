package cupitoo.wtwt.repository.chat;

import cupitoo.wtwt.model.chat.ChatRoom;
import cupitoo.wtwt.model.post.Post;
import cupitoo.wtwt.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomRepositoryCustom {
    ChatRoom findFirstByPostAndUser(Post post, User user);
}
