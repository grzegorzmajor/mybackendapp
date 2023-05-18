package ovh.major.mybackendapp.domain.blog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
class BlogPost {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String addedDate;

    private String content;

}
