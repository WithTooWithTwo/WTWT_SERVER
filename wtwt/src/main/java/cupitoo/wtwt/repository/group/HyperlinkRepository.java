package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.group.Hyperlink;
import cupitoo.wtwt.model.group.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HyperlinkRepository extends JpaRepository<Hyperlink, Long> {
}
