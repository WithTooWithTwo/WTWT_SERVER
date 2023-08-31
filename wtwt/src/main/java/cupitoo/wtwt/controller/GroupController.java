package cupitoo.wtwt.controller;

import cupitoo.wtwt.annotation.Login;
import cupitoo.wtwt.dto.GroupDto;
import cupitoo.wtwt.dto.UserProfile;
import cupitoo.wtwt.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/{id}")
    public GroupDto findGroup(@PathVariable("id") Long id) {
        GroupDto groupDto = groupService.findOne(id);
        return groupDto;
    }

    @GetMapping
    public List<GroupDto> findMyGroups(@Login Long id) {
        return groupService.findMyGroups(id);
    }

    /**
     * 그룹 멤버
     */
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
    @PostMapping("/{groupId}/notice")
    public PostResponse addNotice(@PathVariable("groupId") Long groupId,
                                  @RequestParam("contents") String contents) {
        return new PostResponse(groupService.addNotice(groupId, contents));
    }

    @PatchMapping("/{groupId}/notice/{groupNoticeId}")
    public PostResponse editNotice(@PathVariable("groupId") Long groupId,
                                   @PathVariable("groupNoticeId") Long groupNoticeId,
                                   @RequestParam("contents") String contents) {
        return new PostResponse(groupService.editNotice(groupNoticeId, contents));
    }

    @DeleteMapping("/{groupId}/notice/{groupNoticeId}")
    public ResponseEntity<Object> removeNotion(@PathVariable("groupId") Long groupId,
                                               @PathVariable("groupNoticeId") Long groupNoticeId) {
        groupService.removeNotice(groupNoticeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 메모
     */
    @PostMapping("/{groupId}/memo")
    public PostResponse addMemo(@PathVariable("groupId") Long groupId,
                                @RequestParam("contents") String contents) {
        return new PostResponse(groupService.addMemo(groupId, contents));
    }

    @PatchMapping("/{groupId}/memo/{groupMemoId}")
    public PostResponse editMemo(@PathVariable("groupId") Long groupId,
                                 @PathVariable("groupMemoId") Long groupMemoId,
                                 @RequestParam("contents") String contents) {
        return new PostResponse(groupService.editMemo(groupMemoId, contents));
    }

    @DeleteMapping("/{groupId}/memo/{groupMemoId}")
    public ResponseEntity<Object> removeMemo(@PathVariable("groupId") Long groupId,
                                               @PathVariable("groupMemoId") Long groupMemoId) {
        groupService.removeMemo(groupMemoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
