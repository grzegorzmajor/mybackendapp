package ovh.major.mybackendapp.domain.blog;

import org.springframework.data.jpa.repository.JpaRepository;

interface PostRepository extends JpaRepository<PostEntity, Integer>
{

}