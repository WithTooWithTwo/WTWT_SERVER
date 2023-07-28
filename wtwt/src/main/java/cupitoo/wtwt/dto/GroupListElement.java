package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.group.Group;
import lombok.Data;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Data
public class GroupListElement {
    private Long id;
    private int dday;
    private String image;
    private String name;
    private LocalDate firstDay;
    private UserProfile leader;

    public GroupListElement(Group group) {
        this.id = group.getId();
        this.dday = getDday(group.getFirstDay());
        if(group.getGroupImage() != null) {
            this.image = group.getGroupImage().getFileName();
        }
        this.name = group.getGroupName();
        this.firstDay = group.getFirstDay();
        this.leader = new UserProfile(group.getLeader());
    }

    private int getDday(LocalDate toDate) {
        LocalDate fromDate = LocalDate.now();
        return (int) DAYS.between(fromDate, toDate);
    }
}
