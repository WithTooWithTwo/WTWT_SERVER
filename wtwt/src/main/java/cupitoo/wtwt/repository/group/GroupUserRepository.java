package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.Group.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {
}
