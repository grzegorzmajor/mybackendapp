package ovh.major.mybackendapp.domain.blog.logic;

import org.springframework.data.repository.CrudRepository;


interface ParagraphRepository extends CrudRepository<ParagraphEntity, Integer>
{

    ParagraphEntity findFirstById(Integer id);

}
