package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.Group.GroupUser;
import cupitoo.wtwt.model.Group.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
