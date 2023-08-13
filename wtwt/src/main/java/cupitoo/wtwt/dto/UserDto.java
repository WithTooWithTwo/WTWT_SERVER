package cupitoo.wtwt.dto;

import cupitoo.wtwt.controller.user.GroupListElementsForUserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private UserProfile profileImage;
    private String nickname;
    private Float rate; //별점 평균
    private String statusMessage;

    private Integer countsOfGroups;
    private Integer countsOfPosts;
    private Integer countsOfReviews;

    private List<GroupListElementsForUserInfo> myGroups;
    private List<String> styles;
    private List<String> personalities;
    private List<String> comments;
}
