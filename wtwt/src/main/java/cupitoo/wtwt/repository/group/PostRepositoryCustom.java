package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.dto.PostListElement;
import cupitoo.wtwt.model.Group.Group;
import cupitoo.wtwt.model.Group.Post;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostListElement> findAllWithFilter(PostSearch postSearch);
}
