package cupitoo.wtwt.controller.chat;

import cupitoo.wtwt.dto.UserProfile;
import cupitoo.wtwt.model.chat.ChatRoom;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ChatRoomDtoForList {
    private Long roomId;
    private String title;
    private UserProfile user;
    private String lastMessage;
    private Integer unreadCount;
}
