package ovh.major.mybackendapp.domain.blog.infrastructure.exception;

public class EntityWithIdNotExistException extends RuntimeException {
    public EntityWithIdNotExistException(Integer id) {
        super("ERROR: Entity with id " + id + " not exist.");
    }
}
