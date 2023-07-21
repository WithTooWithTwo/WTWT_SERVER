package cupitoo.wtwt.controller.review;

import cupitoo.wtwt.model.review.Personality;
import cupitoo.wtwt.model.review.Style;
import lombok.Data;

@Data
public class OptionDto {
    Long id;
    String name;

    OptionDto(Personality p) {
        this.id = p.getId();
        this.name = p.getType();
    }

    OptionDto(Style p) {
        this.id = p.getId();
        this.name = p.getType();
    }
}
