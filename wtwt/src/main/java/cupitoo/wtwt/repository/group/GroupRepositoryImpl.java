package cupitoo.wtwt.repository.group;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cupitoo.wtwt.model.group.QGroup.group;

@Repository
@RequiredArgsConstructor
public class GroupRepositoryImpl implements GroupRepositoryCustom{
    private final JPAQueryFactory query;

    @Override
    public List<Group> findIdsByUser(User user) {
        return query.select(group)
                .from(group)
                .join(group.members)
                .where(group.leader.eq(user).or(group.members.any().user.eq(user)))
                .fetch();
    }


}
