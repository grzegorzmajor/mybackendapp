package ovh.major.mybackendapp.domain.blog;

import org.springframework.data.repository.CrudRepository;


interface BlogPostRepository extends CrudRepository<BlogPostEntity, Integer>
{

}
