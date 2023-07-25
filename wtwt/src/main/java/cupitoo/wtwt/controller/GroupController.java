package cupitoo.wtwt.controller;

import cupitoo.wtwt.dto.GroupDto;
import cupitoo.wtwt.service.GroupService;
import cupitoo.wtwt.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
