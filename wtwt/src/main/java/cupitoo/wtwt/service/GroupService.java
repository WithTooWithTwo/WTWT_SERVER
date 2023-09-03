package cupitoo.wtwt.service;

import cupitoo.wtwt.dto.GroupDto;
import cupitoo.wtwt.dto.NotificationDto;
import cupitoo.wtwt.dto.UserProfile;
import cupitoo.wtwt.model.Notification;
import cupitoo.wtwt.model.NotificationType;
import cupitoo.wtwt.model.group.*;
import cupitoo.wtwt.model.user.User;
import cupitoo.wtwt.repository.EmitterRepository;
import cupitoo.wtwt.repository.NotificationRepository;
import cupitoo.wtwt.repository.UserRepository;
import cupitoo.wtwt.repository.group.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupUserRepository groupUserRepository;
    private final GroupNoticeRepository groupNoticeRepository;
    private final GroupLinkRepository groupLinkRepository;
    private final GroupMemoRepository groupMemoRepository;
    private final NoticeRepository noticeRepository;
    private final MemoRepository memoRepository;
    private final HyperlinkRepository hyperlinkRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;

    /**
     * 그룹 조회
     */
    public GroupDto findOne(Long groupId) {
        return new GroupDto(groupRepository.findById(groupId).get());
    }

    public List<GroupDto> findMyGroups(Long userId) {
        User user = userRepository.findById(userId).get();
        return groupRepository.findIdsByUser(user)
                .stream()
                .map(g -> new GroupDto(g))
                .collect(Collectors.toList());
    }

    public List<UserProfile> findMembersWithoutMe(Long loginId, Long groupId) {
        List<UserProfile> result = new ArrayList<>();
        Group group = groupRepository.findById(groupId).get();
        List<GroupUser> members = group.getMembers();
        User leader = group.getLeader();
        if(leader.getId() != loginId) {
            result.add(new UserProfile(leader));
        }

        for(GroupUser member: members) {
            if(member.getUser().getId() == loginId) continue;
            result.add(new UserProfile(member.getUser()));
        }

        return result;
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
        return gn.getId();
    }

    @Transactional
    public Long editNotice(Long groupNoticeId, String contents) {
        GroupNotice gn = groupNoticeRepository.findById(groupNoticeId).get();
        gn.getNotice().changeContents(contents);
        return gn.getNotice().getId();
    }

    @Transactional
    public void removeNotice(Long groupNoticeId) {
        GroupNotice gn = groupNoticeRepository.findById(groupNoticeId).get();
        groupNoticeRepository.delete(gn);
    }

    /**
     * 메모
     */
    @Transactional
    public Long addMemo(Long groupId, String contents) {
        Group group = groupRepository.findById(groupId).get();
        Memo memo = new Memo(contents);
        memoRepository.save(memo);

        GroupMemo gm = new GroupMemo(group, memo);
        group.addMemo(gm);

        return gm.getId();
    }

    @Transactional
    public Long editMemo(Long groupMemoId, String contents) {
        GroupMemo gm = groupMemoRepository.findById(groupMemoId).get();
        gm.getMemo().changeContents(contents);
        return groupMemoId;
    }

    @Transactional
    public void removeMemo(Long groupMemoId) {
        GroupMemo gm = groupMemoRepository.findById(groupMemoId).get();
        groupMemoRepository.delete(gm);
    }

    /**
     * 링크
     */
    @Transactional
    public Long addLink(Long id, String link, String description) {
        Group group = groupRepository.findById(id).get();
        Hyperlink hyperlink = new Hyperlink(link, description);
        hyperlinkRepository.save(hyperlink);

        GroupLink gl = new GroupLink(group, hyperlink);
        group.addLink(gl);
        return group.getId();
    }

    @Transactional
    public Long editLink(Long groupLinkId, String link, String description) {
        GroupLink gl = groupLinkRepository.findById(groupLinkId).get();
        gl.getLink().changeContents(link, description);
        return groupLinkId;
    }

    @Transactional
    public void removeLink(Long groupLinkId) {
        GroupLink gl = groupLinkRepository.findById(groupLinkId).get();
        groupLinkRepository.delete(gl);
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

    /**
     * 그룹초대
     */
    public void inviteToGroup(Long userId, Long groupId) {
        User receiver = userRepository.findById(userId).get();
        Group group = groupRepository.findById(groupId).get();

        Notification notification = Notification.builder()
                .receiver(receiver)
                .group(group)
                .isRead(false)
                .message(group.getLeader().getName() + "님의 " + group.getGroupName())
                .type(NotificationType.INVITATION)
                .build();
        notificationRepository.save(notification);

        NotificationDto data = new NotificationDto(notification);
        notificationService.notify(userId, data);
    }
}
