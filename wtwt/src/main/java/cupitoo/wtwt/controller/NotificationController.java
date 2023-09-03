package cupitoo.wtwt.controller;

import cupitoo.wtwt.annotation.Login;
import cupitoo.wtwt.dto.NotificationDto;
import cupitoo.wtwt.model.Notification;
import cupitoo.wtwt.model.NotificationType;
import cupitoo.wtwt.service.GroupService;
import cupitoo.wtwt.service.NotificationService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final GroupService groupService;

    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@Login Long id) {
        return notificationService.subscribe(id);
    }

    @PostMapping("/{notificationId}")
    public PostResponse checkNotification(@PathVariable Long notificationId, @RequestParam(value = "isAccept", required = false) Boolean isAccept) {
        if(notificationService.isInvite(notificationId)) {
            Notification notification = notificationService.get(notificationId);
            groupService.addMember(notification.getGroup().getId(), notification.getReceiver().getNickname());
        }
        notificationService.checkNotification(notificationId);
        return new PostResponse(notificationId);
    }

    @GetMapping()
    public List<NotificationDto> getNotifications(@RequestParam boolean isInvite, @Login Long userId) {
        return notificationService.getNotifications(isInvite, userId);
    }
}
