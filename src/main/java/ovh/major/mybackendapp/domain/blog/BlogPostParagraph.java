package ovh.major.mybackendapp.domain.blog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

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
    private String paragraphContent;

}
