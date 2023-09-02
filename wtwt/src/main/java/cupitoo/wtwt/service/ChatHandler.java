package cupitoo.wtwt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cupitoo.wtwt.dto.MessageDto;
import cupitoo.wtwt.model.chat.ChatMessage;
import cupitoo.wtwt.model.chat.ChatRoom;
import cupitoo.wtwt.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {
    private final ChatService chatService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    private Map<Long, List<WebSocketSession>> roomMembers = new ConcurrentHashMap<>();
    private Map<WebSocketSession, Long> rooms = new ConcurrentHashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        MessageDto messageDto = objectMapper.readValue(payload, MessageDto.class);

        if(roomMembers.get(messageDto.getRoomId()) == null && messageDto.getMessage().equals("ENTER-CHAT")) {
            ArrayList<WebSocketSession> sessions = new ArrayList<>();
            sessions.add(session);
            rooms.put(session, messageDto.getRoomId());
            roomMembers.put(messageDto.getRoomId(), sessions);
        } else if(roomMembers.get(messageDto.getRoomId()) != null && messageDto.getMessage().equals("ENTER-CHAT")) {
            roomMembers.get(messageDto.getRoomId()).add(session);
            rooms.put(session, messageDto.getRoomId());
        } else if(roomMembers.get(messageDto.getRoomId()) != null && !messageDto.getMessage().equals("ENTER-CHAT")) {
            ChatRoom chatRoom = chatService.findRoomById(messageDto.getRoomId());
            User sender = userService.findUserById(messageDto.getSender());
            ChatMessage chatMessage = new ChatMessage(chatRoom, sender, messageDto.getMessage());
            messageDto.setSendAt(chatService.saveMessage(chatMessage).getCreatedAt());

            for(WebSocketSession member : roomMembers.get(messageDto.getRoomId())) {
                chatService.sendMessage(member, messageDto);
            }
        }

    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }

    /* Client가 접속 해제 시 호출되는 메서드드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if(rooms.get(session) != null) {
            roomMembers.get(rooms.get(session)).remove(session);
            rooms.remove(session);
        }
    }

}
