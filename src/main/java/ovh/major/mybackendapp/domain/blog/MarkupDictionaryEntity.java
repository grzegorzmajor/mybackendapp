package ovh.major.mybackendapp.domain.blog;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class MarkupDictionaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "markup_dictionary_entity_seq")
    @SequenceGenerator(name = "markup_dictionary_entity_seq", sequenceName = "markup_dictionary_entity_seq", allocationSize = 1)
    private Integer id;

    @NotBlank
    @Column(unique = true)
    private String tagName;

    @NotBlank
    private String className;

}
