package ovh.major.mybackendapp.domain.blog.logic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

interface PostRepository extends JpaRepository<PostEntity, Integer> {

    @Query("SELECT e FROM PostEntity e WHERE e.publicationDate <= :date")
    Page<PostEntity> findAllWithPublicationDateTest(@Param("date") Timestamp date, Pageable pageable);

}