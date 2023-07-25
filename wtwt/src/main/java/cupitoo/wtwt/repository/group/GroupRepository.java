package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.post.Post;
import cupitoo.wtwt.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END " +
            "FROM trip_group g JOIN g.members gm WHERE g = :group AND gm.user = :user")
    boolean existsUserInGroup(Group group, User user);

    Group findByPost(Post post);
}
