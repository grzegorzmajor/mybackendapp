package ovh.major.mybackendapp.domain.blog;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
class BlogPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp addedDate;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "paragraph_id")
    private List<BlogPostParagraphEntity> paragraphs;


}
