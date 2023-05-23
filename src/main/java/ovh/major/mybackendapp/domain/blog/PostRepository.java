package ovh.major.mybackendapp.domain.blog;

import org.springframework.data.repository.CrudRepository;


interface PostRepository extends CrudRepository<PostEntity, Integer>
{

}
