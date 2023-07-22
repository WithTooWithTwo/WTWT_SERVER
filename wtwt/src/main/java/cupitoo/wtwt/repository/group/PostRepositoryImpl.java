package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.dto.PostListElement;
import cupitoo.wtwt.model.Group.Post;

import java.util.List;

public class PostRepositoryImpl implements PostRepositoryCustom {
    //private final JPAQueryFactory query;
    @Override
    public List<PostListElement> findAllWithFilter(PostSearch postSearch) {
        return null;
    }
}
