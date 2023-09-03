package cupitoo.wtwt.model;

import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Notification extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User receiver;
    private String message;
    private boolean isRead;
    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    public void readNotification() {
        this.isRead = true;
    }
}
