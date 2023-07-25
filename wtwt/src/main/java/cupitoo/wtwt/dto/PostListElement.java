package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.group.RecruitStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class PostListElement {
    private Long id;
    private UserProfile writer;
    private LocalDateTime createdAt;
    private String title;
    private String content;
    private Boolean isLightning;
    private RecruitStatus status;
    private int preferHeadCount;
    private int headCount;
    private int hits;
}
