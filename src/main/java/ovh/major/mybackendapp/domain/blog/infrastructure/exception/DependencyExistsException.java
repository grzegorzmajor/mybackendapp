package ovh.major.mybackendapp.domain.blog.infrastructure.exception;

public class DependencyExistsException extends RuntimeException {
    public DependencyExistsException() {
        super("ERROR: Deleting not possible because dependency is exist.");
    }
}
