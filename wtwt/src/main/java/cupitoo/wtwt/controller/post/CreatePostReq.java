package cupitoo.wtwt.controller.post;

import cupitoo.wtwt.dto.CategoryDto;
import cupitoo.wtwt.model.user.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class CreatePostReq {
    // 게시글
    private List<MultipartFile> images = new ArrayList<>();
    @NotNull
    private String title;
    @NotNull
    private String content;

    // 그룹
    private List<String> members;
    @NotNull
    private Long category_id;
    private String firstDay;
    private String lastDay;
    private Boolean lightning;
    private List<String> tags;

    // 선호 동행 정보
    private Integer preferHeadCount; // 원하는 동행 인원
    private Gender preferGender;
    private Integer preferMinAge;
    private Integer preferMaxAge;
}
