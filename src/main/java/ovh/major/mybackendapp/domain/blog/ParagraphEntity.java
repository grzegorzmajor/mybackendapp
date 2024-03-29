package ovh.major.mybackendapp.domain.blog;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
class ParagraphEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paragraph_entity_seq")
    @SequenceGenerator(name = "paragraph_entity_seq", sequenceName = "paragraph_entity_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotBlank
    @JoinColumn(name = "dictionary_id")
    private MarkupDictionaryEntity tag;

    @NotBlank
    //@Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "The value cannot contain special characters.")
    @ElementCollection
    private List<String> paragraphContent;

}
