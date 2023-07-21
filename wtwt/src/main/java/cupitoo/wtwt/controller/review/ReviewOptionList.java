package cupitoo.wtwt.controller.review;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ReviewOptionList {
    List<OptionDto> options = new ArrayList<>();
}
