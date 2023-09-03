package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.Notification;
import cupitoo.wtwt.model.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NotificationDto {
    private Long id;
    private String message;
    private NotificationType type;
    private Long groupId;
    private boolean isRead;
    private LocalDateTime createdAt;

    public NotificationDto(Notification notification) {
        this.id = notification.getId();
        this.message = notification.getMessage();
        this.groupId = notification.getGroup().getId();
        this.type = notification.getType();
        this.isRead = notification.isRead();
        this.createdAt = notification.getCreatedAt();
    }
}
