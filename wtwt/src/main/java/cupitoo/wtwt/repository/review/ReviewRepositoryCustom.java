package cupitoo.wtwt.repository.review;

import com.querydsl.core.Tuple;
import cupitoo.wtwt.model.user.User;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Tuple> getUserStyles(User user);
    List<Tuple> getUserPersonality(User user);
}
