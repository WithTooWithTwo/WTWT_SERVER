package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.group.GroupMemo;
import cupitoo.wtwt.model.group.GroupNotice;

public class StringDataDto {
    private Long id;
    private String data;

    public StringDataDto(GroupMemo memo) {
        this.id = memo.getId();
        this.data = memo.getMemo().getContents();
    }
    public StringDataDto(GroupNotice notice) {
        this.id = notice.getId();
        this.data = notice.getNotice().getContents();
    }
}
