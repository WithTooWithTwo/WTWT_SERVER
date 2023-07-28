package cupitoo.wtwt.controller.post;

import cupitoo.wtwt.dto.CategoryDto;
import cupitoo.wtwt.model.user.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Data
public class CreatePostReq {
    // 게시글
    private Optional<List<MultipartFile>> images;
    @NotNull
    private String title;
    @NotNull
    private String content;

    // 그룹
    private Optional<List<String>> members;
    @NotNull
    private Long category_id;
    private String firstDay;
    private String lastDay;
    private Optional<Boolean> lightning;

    // 선호 동행 정보
    private Integer preferHeadCount; // 원하는 동행 인원
    private Optional<Gender> preferGender;
    private Optional<Integer> preferMinAge;
    private Optional<Integer> preferMaxAge;
}
