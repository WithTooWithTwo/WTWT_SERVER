package cupitoo.wtwt.dto;

import cupitoo.wtwt.controller.user.GroupListElementsForUserInfo;
import cupitoo.wtwt.model.Image;
import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.review.Personality;
import cupitoo.wtwt.model.review.Style;
import cupitoo.wtwt.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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
