package ovh.major.mybackendapp.domain.blog.logic;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
import java.util.List;

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotBlank
    @JoinColumn(name = "dictionary_id")
    private MarkupDictionaryEntity tag;

    @NotBlank
    //@Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "The value cannot contain special characters.")
    @ElementCollection
    private List<String> paragraphContent;

}
