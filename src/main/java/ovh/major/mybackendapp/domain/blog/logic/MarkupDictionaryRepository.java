package ovh.major.mybackendapp.domain.blog.logic;

import org.springframework.data.repository.CrudRepository;


interface MarkupDictionaryRepository extends CrudRepository<MarkupDictionaryEntity, Integer> {

    MarkupDictionaryEntity findFirstByOpening(String opening);
}
