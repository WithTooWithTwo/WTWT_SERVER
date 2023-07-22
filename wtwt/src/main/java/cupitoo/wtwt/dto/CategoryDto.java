package cupitoo.wtwt.dto;

import cupitoo.wtwt.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
