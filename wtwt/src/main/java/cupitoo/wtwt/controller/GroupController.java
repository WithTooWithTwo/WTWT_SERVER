package cupitoo.wtwt.controller;

import cupitoo.wtwt.annotation.Login;
import cupitoo.wtwt.dto.GroupDto;
import cupitoo.wtwt.dto.UserProfile;
import cupitoo.wtwt.service.GroupService;
import cupitoo.wtwt.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final FileStore fileStore;

    @GetMapping("/{id}")
    public GroupDto findGroup(@PathVariable("id") Long id) {
        GroupDto groupDto = groupService.findOne(id);
        return groupDto;
    }

    /**
     * 그룹 멤버
     */
    @GetMapping
    public List<GroupDto> findMyGroups(@Login Long id) {
        return groupService.findMyGroups(id);
    }

    @GetMapping("/{id}/members")
    public List<UserProfile> findGroupMemberWithoutMe(@Login Long loginId, @PathVariable("id") Long groupId) {
        return groupService.findMembersWithoutMe(loginId, groupId);
    }

    @PatchMapping("/{id}/member")
    public PostResponse addMember(@PathVariable("id") Long groupId,
                                    @RequestParam("nickname") String nickname) {
        return new PostResponse(groupService.addMember(groupId, nickname));
    }
    @DeleteMapping ("/{id}/member")
    public PostResponse deleteMember(@PathVariable("id") Long groupId,
                                  @RequestParam("member") Long memberId) {
        groupService.deleteMember(groupId, memberId);
        return new PostResponse(200L);
    }

    /**
     * 공지사항
     */
    @PostMapping("/{id}/notice")
    public PostResponse addNotice(@PathVariable("id") Long groupId,
                                  @RequestParam("contents") String contents) {
        return new PostResponse(groupService.addNotice(groupId, contents));
    }

    @PatchMapping("/{id}/notice/{noticeId}")
    public PostResponse editNotice(@PathVariable("id") Long groupId,
                                   @PathVariable("noticeId") Long noticeId,
                                  @RequestParam("contents") String contents) {
        return new PostResponse(groupService.editNotice(groupId, noticeId, contents));
    }

    @DeleteMapping("/{id}/notice/{noticeId}")
    public PostResponse editNotice(@PathVariable("id") Long groupId,
                                   @PathVariable("noticeId") Long noticeId) {
        return new PostResponse(200L);
    }

    /**
     * 메모
     */
    @PostMapping("/{id}/memo")
    public PostResponse addMemo(@PathVariable("id") Long id,
                                  @RequestParam("contents") String contents) {
        return new PostResponse(groupService.addMemo(id, contents));
    }

    /**
     * 링크
     */
    @PostMapping("/{id}/link")
    public PostResponse addLink(@PathVariable("id") Long id,
                                @RequestParam("link") String link,
                                @RequestParam("description") String description) {
        return new PostResponse(groupService.addLink(id, link, description));
    }

}
