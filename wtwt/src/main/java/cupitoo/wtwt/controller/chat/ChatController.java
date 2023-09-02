package cupitoo.wtwt.controller.chat;

import cupitoo.wtwt.annotation.Login;
import cupitoo.wtwt.controller.PostResponse;
import cupitoo.wtwt.dto.ChatRoomDetail;
import cupitoo.wtwt.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public PostResponse createRoom(@Login Long id, @RequestParam Long postId) {
        return new PostResponse(chatService.createRoom(postId, id));
    }

    @GetMapping
    public List<ChatRoomDtoForList> getAllMyRoom(@Login Long id){
        return chatService.findAllMyRoom(id);
    }

    @GetMapping("/{roomId}")
    public ChatRoomDetail getChatDetail(@Login Long id, @PathVariable Long roomId) {
        return chatService.getChatDetail(id, roomId);
    }
}
