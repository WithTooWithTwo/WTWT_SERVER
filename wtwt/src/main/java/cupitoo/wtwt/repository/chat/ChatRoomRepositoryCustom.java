package cupitoo.wtwt.repository.chat;

import cupitoo.wtwt.model.chat.ChatRoom;
import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.user.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepositoryCustom {
    List<ChatRoom> findRoomsByUser(User user);
}
