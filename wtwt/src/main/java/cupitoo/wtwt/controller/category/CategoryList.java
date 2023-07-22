package cupitoo.wtwt.controller.category;

import cupitoo.wtwt.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryList {
    private List<CategoryDto> categories;
}
