package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.post.Post;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> findAllWithFilter(PostSearch postSearch);
}
