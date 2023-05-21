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
class BlogMarkupDictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @Pattern(regexp = "<([^\\s/\\\\]).*>", message = "The opening tag must start with '<', the second character cannot be a space or / \\ and end with '>'")
    @Column(unique = true)
    private String opening;

    @NotBlank
    @Pattern(regexp = "</[^\\s]*>", message = "The string must start with '</' and end with '>' without containing spaces")
    private String closing;
}
