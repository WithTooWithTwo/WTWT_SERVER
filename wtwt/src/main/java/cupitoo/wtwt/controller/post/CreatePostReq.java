package cupitoo.wtwt.controller.post;

import cupitoo.wtwt.model.User.Gender;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreatePostReq {
    // 게시글
    private List<MultipartFile> images = new ArrayList<>();
    private String title;
    private String content;

    // 그룹
    private List<String> members = new ArrayList<>();
    private Long category_id;
    private LocalDate firstDay;
    private LocalDate lastDay;
    private boolean lightning = false;

    // 선호 동행 정보
    private Integer preferHeadCount; // 원하는 동행 인원
    private Gender preferGender;
    private Integer preferMinAge;
    private Integer preferMaxAge;
}
