package cupitoo.wtwt.controller;

import cupitoo.wtwt.annotation.Login;
import cupitoo.wtwt.controller.login.LoginReq;
import cupitoo.wtwt.dto.GroupDto;
import cupitoo.wtwt.dto.GroupListElement;
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

    @GetMapping("/{id}/members")
    public List<UserProfile> findGroupMember(@PathVariable("id") Long id) {
        return groupService.findMembers(id);
    }

    @GetMapping
    public List<GroupListElement> findMyGroups(@Login Long id) {
        return groupService.findMyGroups(id);
    }

    @PatchMapping("/{id}/notice")
    public PostResponse addNotice(@PathVariable("id") Long id,
                                  @RequestParam("contents") String contents) {
        return new PostResponse(groupService.addNotice(id, contents));
    }

    @PatchMapping("/{id}/memo")
    public PostResponse addMemo(@PathVariable("id") Long id,
                                  @RequestParam("contents") String contents) {
        return new PostResponse(groupService.addMemo(id, contents));
    }

    @PatchMapping("/{id}/link")
    public PostResponse addLink(@PathVariable("id") Long id,
                                @RequestParam("link") String link,
                                @RequestParam("description") String description) {
        return new PostResponse(groupService.addLink(id, link, description));
    }

}
