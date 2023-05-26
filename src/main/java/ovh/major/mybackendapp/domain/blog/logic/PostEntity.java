package ovh.major.mybackendapp.domain.blog.logic;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp addingDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp publicationDate;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "paragraph_id")
    private List<ParagraphEntity> paragraphs;

}
