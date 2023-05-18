package ovh.major.mybackendapp.domain.blog;

import org.springframework.data.repository.CrudRepository;

interface BlogRepository extends CrudRepository<BlogPost, Integer> {
}
