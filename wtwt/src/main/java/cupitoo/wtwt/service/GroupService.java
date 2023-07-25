package cupitoo.wtwt.service;

import cupitoo.wtwt.dto.GroupDto;
import cupitoo.wtwt.repository.group.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
