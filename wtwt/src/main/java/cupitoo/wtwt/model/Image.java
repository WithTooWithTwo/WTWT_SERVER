package cupitoo.wtwt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {
    @Id @GeneratedValue()
    @Column(name = "image_id")
    private Long id;
    @NotEmpty
    private String fileName;
    public Image(String fileName) {
        this.fileName = fileName;
    }
}
