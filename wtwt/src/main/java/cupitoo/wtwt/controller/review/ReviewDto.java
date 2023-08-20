package cupitoo.wtwt.controller.review;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Data
public class ReviewDto {
    @NotNull
    @Range(min = 0, max = 5) //별점은 0 - 5 사이의 정수
    private Integer rate;
    @NotNull
    private Long receiverId;
    private List<Long> personalities;
    private List<Long> styles;
    private String comment;
}
