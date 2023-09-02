package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.group.Group;
import cupitoo.wtwt.model.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PostDtoForChat {
    private Long id;
    private String title;
    private LocalDate firstDay;
    private LocalDate lastDay;
    private String image;

    public PostDtoForChat(Post post, Group group) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.firstDay = group.getFirstDay();
        this.lastDay = group.getLastDay();
        if(post.getImages().size() != 0) {
            this.image = post.getImages().get(0).getImage().getUrl();
        }
    }
}
