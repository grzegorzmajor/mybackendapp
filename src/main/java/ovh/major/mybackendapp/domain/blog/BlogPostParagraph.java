package ovh.major.mybackendapp.domain.blog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class BlogPostParagraph {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private Integer paragraphType;

    @NotBlank
    private String paragraphContent;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private BlogPost blogPost;

}
