package cupitoo.wtwt.controller;

import cupitoo.wtwt.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {
    //private final GroupService groupService;
    private final FileStore fileStore;
}
