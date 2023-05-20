package ovh.major.mybackendapp.domain.blog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
class BlogMarkupDictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @Pattern(regexp = "<([^\\s/\\\\]).*>", message = "The opening tag must start with '<', the second character cannot be a space or / \\ and end with '>'")
    private String opening;

    @NotBlank
    @Pattern(regexp = "</[^\\s]*>", message = "The string must start with '</' and end with '>' without containing spaces")
    private String closing;
}
