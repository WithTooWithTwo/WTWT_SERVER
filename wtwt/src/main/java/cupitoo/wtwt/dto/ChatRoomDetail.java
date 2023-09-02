package cupitoo.wtwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChatRoomDetail {
    PostDtoForChat post;
    UserProfile userProfile;
    List<MessageDto> messages;
}
