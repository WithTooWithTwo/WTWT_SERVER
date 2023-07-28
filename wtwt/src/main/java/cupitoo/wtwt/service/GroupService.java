package cupitoo.wtwt.service;

import cupitoo.wtwt.dto.GroupDto;
import cupitoo.wtwt.dto.GroupListElement;
import cupitoo.wtwt.dto.UserProfile;
import cupitoo.wtwt.model.group.*;
import cupitoo.wtwt.model.user.User;
import cupitoo.wtwt.repository.UserRepository;
import cupitoo.wtwt.repository.group.GroupRepository;
import cupitoo.wtwt.repository.group.HyperlinkRepository;
import cupitoo.wtwt.repository.group.MemoRepository;
import cupitoo.wtwt.repository.group.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;
    private final MemoRepository memoRepository;
    private final HyperlinkRepository hyperlinkRepository;

    /**
     * 그룹 조회
     */
    public GroupDto findOne(Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        return new GroupDto(groupRepository.findById(groupId).get());
    }

    public List<UserProfile> findMembers(Long groupId) {
        List<UserProfile> result = new ArrayList<>();
        Group group = groupRepository.findById(groupId).get();
        List<GroupUser> members = group.getMembers();
        User leader = group.getLeader();
        result.add(new UserProfile(leader));

        for (GroupUser member: members) {
            result.add(new UserProfile(member.getUser()));
        }

        return result;
    }

    public List<GroupListElement> findMyGroups(Long userId) {
        User user = userRepository.findById(userId).get();
        return groupRepository.findAllByLeader(user).stream()
                .map(g -> new GroupListElement(g))
                .collect(Collectors.toList());
    }

    /**
     * 공지 추가
     */
    @Transactional
    public Long addNotice(Long id, String contents) {
        Group group = groupRepository.findById(id).get();
        Notice notice = new Notice(contents);
        noticeRepository.save(notice);

        GroupNotice gn = new GroupNotice(group, notice);
        group.addNotice(gn);

        return group.getId();
    }

    @Transactional
    public Long addMemo(Long id, String contents) {
        Group group = groupRepository.findById(id).get();
        Memo memo = new Memo(contents);
        memoRepository.save(memo);

        GroupMemo gm = new GroupMemo(group, memo);
        group.addMemo(gm);
        return group.getId();
    }

    @Transactional
    public Long addLink(Long id, String link, String description) {
        Group group = groupRepository.findById(id).get();
        Hyperlink hyperlink = new Hyperlink(link, description);
        hyperlinkRepository.save(hyperlink);

        GroupLink gl = new GroupLink(group, hyperlink);
        group.addLink(gl);
        return group.getId();
    }
}
