package cupitoo.wtwt.repository.chat;

import com.querydsl.jpa.impl.JPAQueryFactory;
import cupitoo.wtwt.model.chat.ChatRoom;
import cupitoo.wtwt.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cupitoo.wtwt.model.chat.QChatRoom.chatRoom;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom{
    private final JPAQueryFactory query;

    @Override
    public List<ChatRoom> findRoomsByUser(User user) {
        return query.select(chatRoom)
                .from(chatRoom)
                .join(chatRoom.post)
                .where(chatRoom.user.eq(user).or(chatRoom.post.createdBy.eq(user)))
                .fetch();
    }
}
