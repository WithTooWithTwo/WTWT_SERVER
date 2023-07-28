package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.post.Post;
import cupitoo.wtwt.model.post.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    List<PostImage> findByPost(Post post);
}
