package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.group.GroupNotice;
import cupitoo.wtwt.model.group.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupNoticeRepository extends JpaRepository<GroupNotice, Long> {
}
