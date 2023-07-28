package cupitoo.wtwt.repository.group;

import cupitoo.wtwt.model.post.Post;
import cupitoo.wtwt.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
@Query("SELECT COUNT(p)" +
        "FROM Post p WHERE p.createdBy = :user")
    int countPostByUser(@Param("user") User user);
}
