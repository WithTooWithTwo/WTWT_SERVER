package cupitoo.wtwt.service;

import cupitoo.wtwt.dto.GroupDto;
import cupitoo.wtwt.dto.UserProfile;
import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.group.GroupUser;
import cupitoo.wtwt.model.user.User;
import cupitoo.wtwt.repository.group.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    /**
     * 그룹 조회
     */
    public GroupDto findOne(Long groupId) {
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
}
