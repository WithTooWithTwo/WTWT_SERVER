package cupitoo.wtwt.repository.chat;


import cupitoo.wtwt.model.chat.ChatMessage;
import cupitoo.wtwt.model.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    ChatMessage findFirstByChatRoomOrderByCreatedAtDesc(@Param("room") ChatRoom room);

    List<ChatMessage> findAllByChatRoom(ChatRoom room);;
}
