package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.group.GroupMemo;
import cupitoo.wtwt.model.group.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMemoRepository extends JpaRepository<GroupMemo, Long> {
}
