package ovh.major.mybackendapp.domain.blog;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


interface ParagraphRepository extends CrudRepository<ParagraphEntity, Integer> {
    ParagraphEntity findFirstById(Integer id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM ParagraphEntity p WHERE p.tag.id = :dictionaryId")
    boolean checkDependencies(@Param("dictionaryId") Integer dictionaryId);

}
