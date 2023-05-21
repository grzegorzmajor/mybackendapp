package ovh.major.mybackendapp.domain.blog;

import org.springframework.data.repository.CrudRepository;


interface BlogPostParagraphRepository extends CrudRepository<BlogPostParagraphEntity, Integer>
{

}
