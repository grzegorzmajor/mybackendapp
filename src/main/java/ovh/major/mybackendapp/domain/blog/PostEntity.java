package ovh.major.mybackendapp.domain.blog;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_entity_seq")
    @SequenceGenerator(name = "post_entity_seq", sequenceName = "post_entity_seq", allocationSize = 1)
    private Integer id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp addingDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp publicationDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn(name = "paragraph_id")
    private List<ParagraphEntity> paragraphs;

}
