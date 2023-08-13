package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.user.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepositoryCustom {
    List<Group> findIdsByUser(@Param("user") User user);
}
