package ovh.major.mybackendapp.domain.blog;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


interface MarkupDictionaryRepository extends CrudRepository<MarkupDictionaryEntity, Integer> {

    MarkupDictionaryEntity findFirstByTagName(String tagName);

    @Modifying
    @Query("DELETE MarkupDictionaryEntity m WHERE m.id = :id")
    void deleteById(@Param("id") Integer id);

}
