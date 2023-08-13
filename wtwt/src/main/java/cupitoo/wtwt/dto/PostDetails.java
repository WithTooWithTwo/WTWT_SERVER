package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.post.Post;
import cupitoo.wtwt.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostDetails {
    private Long post_id;
    private String title;
    private Integer hits;
    private LocalDateTime postDate;
    private UserProfile writer;
    private Boolean lightning;
    //==============================
    private LocalDate firstDay;
    private LocalDate lastDay;
    private List<String> tags;
    private List<UserProfile> members;
    private PreferenceDto preference;

    //==============================
    private String content;
    List<String> images;

    private CategoryDto category;

    public PostDetails(Post post, Group group, List<User> members) {
        this.post_id = post.getId();
        this.title = post.getTitle();
        this.hits = post.getHits();
        this.postDate = post.getCreatedAt();
        this.writer = new UserProfile(post.getCreatedBy());
        this.lightning = group.isLightning();
        this.firstDay = group.getFirstDay();
        this.lastDay = group.getLastDay();
        this.members = members.stream()
                .map(m -> new UserProfile(m))
                .collect(Collectors.toList());
        this.preference = new PreferenceDto(group.getPreference());
        this.content = post.getText();
        this.images = post.getImages().stream()
                .map(i -> i.getImage().getUrl())
                .collect(Collectors.toList());
        this.category = new CategoryDto(group.getCategory());
    }
}
