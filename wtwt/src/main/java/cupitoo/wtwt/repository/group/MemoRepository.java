package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.group.Memo;
import cupitoo.wtwt.model.group.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
