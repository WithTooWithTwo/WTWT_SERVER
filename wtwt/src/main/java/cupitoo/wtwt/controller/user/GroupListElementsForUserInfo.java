package cupitoo.wtwt.controller.user;

import cupitoo.wtwt.dto.UserProfile;
import cupitoo.wtwt.model.group.Group;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GroupListElementsForUserInfo {
    private Long id;
    private String name;
    private LocalDate firstDay;
    private LocalDate lastDay;
    private List<UserProfile> members = new ArrayList<>();

    public GroupListElementsForUserInfo(Group group) {
        this.id = group.getId();
        this.name = group.getGroupName();
        this.firstDay = group.getFirstDay();
        this.lastDay = group.getLastDay();
        if(group.getMembers() != null) {
            this.members = group.getMembers().stream()
                    .map(m -> new UserProfile(m.getUser()))
                    .collect(Collectors.toList());
        }
    }
}
