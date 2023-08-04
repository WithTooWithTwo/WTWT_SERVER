package cupitoo.wtwt.service;

import cupitoo.wtwt.dto.GroupDto;
import cupitoo.wtwt.dto.GroupListElement;
import cupitoo.wtwt.dto.UserProfile;
import cupitoo.wtwt.model.group.*;
import cupitoo.wtwt.model.user.User;
import cupitoo.wtwt.repository.UserRepository;
import cupitoo.wtwt.repository.group.*;
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
    private final GroupUserRepository groupUserRepository;
    private final GroupNoticeRepository groupNoticeRepository;
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

    public List<UserProfile> findMembersWithoutMe(Long loginId, Long groupId) {
        List<UserProfile> result = new ArrayList<>();
        Group group = groupRepository.findById(groupId).get();
        List<GroupUser> members = group.getMembers();
        User leader = group.getLeader();
        if(leader.getId() != loginId) {
            result.add(new UserProfile(leader));
        }

        for (GroupUser member: members) {
            if(member.getId() == loginId) continue;
            result.add(new UserProfile(member.getUser()));
        }

        return result;
    }

    public List<GroupDto> findMyGroups(Long userId) {
        User user = userRepository.findById(userId).get();
        return groupRepository.findAllByLeader(user).stream()
                .map(g -> new GroupDto(g))
                .collect(Collectors.toList());
    }

    /**
     * 공지
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
    public Long editNotice(Long groupId, Long noticeId,String contents) {
        Group group = groupRepository.findById(groupId).get();
        Notice notice = noticeRepository.findById(noticeId).get();
        notice.changeContents(contents);
        return noticeId;
    }

    @Transactional
    public void removeNotice(Long groupId, Long noticeId) {
        Group group = groupRepository.findById(groupId).get();
        GroupNotice gn = groupNoticeRepository.findById(noticeId).get();
        group.removeNotice(gn);
        groupNoticeRepository.delete(gn);
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

    /**
     * 멤버
     */
    @Transactional
    public Long addMember(Long groupId, String nickname) {
        Group group = groupRepository.findById(groupId).get();
        User member = userRepository.findByNickname(nickname);
        if(member == null) throw new IllegalArgumentException("존재하지 않는 유저입니다.");
        if(groupUserRepository.findGroupUserByGroupAndUser(group, member) != null) {
            throw new IllegalArgumentException("이미 그룹에 추가된 유저입니다.");
        }
        GroupUser groupUser = new GroupUser(group, member);
        groupUserRepository.save(groupUser);
        group.addMember(groupUser);
        return member.getId();
    }

    @Transactional
    public void deleteMember(Long groupId, Long memberId) {
        Group group = groupRepository.findById(groupId).get();
        User member = userRepository.findById(memberId).get();
        GroupUser gu = groupUserRepository.findGroupUserByGroupAndUser(group, member);
        group.removeMember(gu);
        groupUserRepository.delete(gu);
    }
}
