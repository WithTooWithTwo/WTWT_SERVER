package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.group.GroupLink;
import lombok.Data;

@Data
public class HyperlinkDto {
    private Long id;
    private String link;
    protected String name;

    public HyperlinkDto(GroupLink link) {
        this.id = link.getId();
        this.link = link.getLink().getLink();
        this.name = link.getLink().getName();
    }
}
