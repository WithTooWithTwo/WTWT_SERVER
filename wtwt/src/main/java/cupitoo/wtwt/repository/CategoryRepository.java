package cupitoo.wtwt.repository;

import cupitoo.wtwt.model.Category;
import cupitoo.wtwt.model.Group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
