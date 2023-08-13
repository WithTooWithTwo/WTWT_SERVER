package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.group.GroupUser;
import cupitoo.wtwt.model.post.Post;
import cupitoo.wtwt.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long>, GroupRepositoryCustom {
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END " +
            "FROM trip_group g JOIN g.members gm WHERE g = :group AND (gm.user = :user OR g.leader = :user)")
    boolean existsUserInGroup(@Param("group") Group group, @Param("user") User user);

    @Query("SELECT COUNT(g)" +
            "FROM trip_group g JOIN g.members gm " +
            "WHERE (gm.user = :user OR g.leader = :user)")
    int countGroupByUser(@Param("user") User user);
    Group findByPost(Post post);
    List<Group> findAllByLeader(User user);

//    @Query("SELECT g " +
//            "FROM trip_group g JOIN FETCH g.members gm " +
//            "WHERE (:gu IN gm OR g.leader = :leader)")
//    List<Group> findAllByMemberAndLeader(@Param("gu") GroupUser member, @Param("leader") User leader);
}
