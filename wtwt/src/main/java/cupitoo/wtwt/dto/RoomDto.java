package cupitoo.wtwt.dto;

import cupitoo.wtwt.service.ChatService;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Data
public class RoomDto {
    private Long roomId;
    private Set<WebSocketSession> members = new HashSet<>();

    public RoomDto(Long roomId) {
        this.roomId = roomId;
    }

    public void handleMassage(WebSocketSession session, MessageDto message, ChatService chatService) throws IOException {
        sendMessage(message, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService) throws IOException {
        for(WebSocketSession member : members) {
            chatService.sendMessage(member, message);
        }
    }
}
