package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MessageDto {
    private Long roomId;
    private Long sender;
    private String message;
    private LocalDateTime sendAt;

    public MessageDto(ChatMessage message) {
        this.roomId = message.getChatRoom().getId();
        this.sender = message.getSender().getId();
        this.message = message.getMessage();
        this.sendAt = message.getCreatedAt();
    }
}
