package ovh.major.mybackendapp.domain.blog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
class BlogPostParagraph {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @NotBlank
    private BlogMarkupDictionary tag;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "The value cannot contain special characters.")
    private String paragraphContent;

}
