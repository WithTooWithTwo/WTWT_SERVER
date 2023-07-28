package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.group.GroupUser;
import cupitoo.wtwt.model.group.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
