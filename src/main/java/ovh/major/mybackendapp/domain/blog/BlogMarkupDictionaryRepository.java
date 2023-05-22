package ovh.major.mybackendapp.domain.blog;

import org.springframework.data.repository.CrudRepository;


interface BlogMarkupDictionaryRepository extends CrudRepository<BlogMarkupDictionaryEntity, Integer>
{
    BlogMarkupDictionaryEntity findFirstByOpening(String opening);

}
