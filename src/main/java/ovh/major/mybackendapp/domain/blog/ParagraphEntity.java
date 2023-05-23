package ovh.major.mybackendapp.domain.blog;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
class ParagraphEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @NotBlank
    @JoinColumn(name = "dictionary_id")
    private MarkupDictionaryEntity tag;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "The value cannot contain special characters.")
    private String paragraphContent;

}
