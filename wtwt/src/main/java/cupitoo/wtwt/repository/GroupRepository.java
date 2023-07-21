package cupitoo.wtwt.repository;

import cupitoo.wtwt.model.Group.Group;
import cupitoo.wtwt.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END " +
            "FROM Group g JOIN g.members gm WHERE g = :group AND gm.user = :user")
    boolean existsUserInGroup(Group group, User user);
}
