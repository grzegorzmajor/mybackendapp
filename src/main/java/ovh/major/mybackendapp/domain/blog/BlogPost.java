package ovh.major.mybackendapp.domain.blog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

    @OneToMany
    private List<BlogPostParagraph> paragraphs;

}
