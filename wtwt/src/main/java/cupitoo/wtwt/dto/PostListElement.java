package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.Group.RecruitStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostListElement {
    UserProfile writer;
    private LocalDateTime createdAt;
    private String title;
    private String content;
    private RecruitStatus status;
    private int preferHeadCount;
    private int headCount;
    private int hits;
}
