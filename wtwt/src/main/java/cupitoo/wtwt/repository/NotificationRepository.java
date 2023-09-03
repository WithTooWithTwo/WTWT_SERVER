package cupitoo.wtwt.repository;

import cupitoo.wtwt.model.Notification;
import cupitoo.wtwt.model.NotificationType;
import cupitoo.wtwt.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> getAllByReceiverAndType(User receiver, NotificationType type);
    List<Notification> getAllByReceiverAndTypeNot(User receiver, NotificationType type);
}
