package cupitoo.wtwt.service;

import com.amazonaws.services.s3.internal.eventstreaming.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cupitoo.wtwt.controller.chat.ChatRoomDtoForList;
import cupitoo.wtwt.dto.ChatRoomDetail;
import cupitoo.wtwt.dto.MessageDto;
import cupitoo.wtwt.dto.PostDtoForChat;
import cupitoo.wtwt.dto.UserProfile;
import cupitoo.wtwt.model.chat.ChatMessage;
import cupitoo.wtwt.model.chat.ChatRoom;
import cupitoo.wtwt.model.post.Post;
import cupitoo.wtwt.model.user.User;
import cupitoo.wtwt.repository.chat.ChatMessageRepository;
import cupitoo.wtwt.repository.chat.ChatRoomRepository;
import cupitoo.wtwt.repository.UserRepository;
import cupitoo.wtwt.repository.group.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ObjectMapper objectMapper;
    private final ChatRoomRepository roomRepository;
    private final ChatMessageRepository messageRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<ChatRoomDtoForList> findAllMyRoom(Long id) {
        User me = userRepository.findById(id).get();
        List<ChatRoom> myRooms = roomRepository.findRoomsByUser(me);

        List<ChatRoomDtoForList> result = new ArrayList<>();
        for(ChatRoom room: myRooms) {
            ChatRoomDtoForList.ChatRoomDtoForListBuilder element = ChatRoomDtoForList.builder()
                    .roomId(room.getId())
                    .title(room.getPost().getTitle())
                    .user(new UserProfile((room.getUser() == me) ? room.getPost().getCreatedBy() : room.getUser()));

            ChatMessage message = messageRepository.findFirstByChatRoomOrderByCreatedAtDesc(room);
            if(message != null) {
                element.lastMessage(message.getMessage());
            }

            result.add(element.build());
        }

        return result;
    }

    public ChatMessage findLaseMessage(ChatRoom room) {
        return messageRepository.findFirstByChatRoomOrderByCreatedAtDesc(room);
    }

    @Transactional
    public Long createRoom(Long postId, Long userId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(userId).get();

        ChatRoom check = roomRepository.findFirstByPostAndUser(post, user);
        if(check != null) return check.getId();

        ChatRoom room = new ChatRoom(post, user);
        roomRepository.save(room);
        return room.getId();
    }

    public ChatRoom findRoomById(Long id) {
        return roomRepository.findById(id).get();
    }

    public ChatMessage saveMessage(ChatMessage message) {
        return messageRepository.save(message);
    }

    public <T> void sendMessage(WebSocketSession session, T message) throws IOException {
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
    }

    public ChatRoomDetail getChatDetail(Long userId, Long roomId) {
        ChatRoom room = roomRepository.findById(roomId).get();
        User me = userRepository.findById(userId).get();
        User theOther;
        if(room.getUser() == me) {
            theOther = room.getPost().getCreatedBy();
        } else theOther = room.getUser();

        List<MessageDto> messages = messageRepository.findAllByChatRoom(room)
                .stream()
                .map(m -> new MessageDto(m))
                .collect(Collectors.toList());

        return new ChatRoomDetail(new PostDtoForChat(room.getPost(), room.getPost().getGroup()),
                new UserProfile(theOther), messages);
    }
}
