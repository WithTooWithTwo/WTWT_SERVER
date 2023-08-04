package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.group.GroupUser;
import cupitoo.wtwt.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {
    GroupUser findGroupUserByGroupAndUser(Group group, User user);
}
