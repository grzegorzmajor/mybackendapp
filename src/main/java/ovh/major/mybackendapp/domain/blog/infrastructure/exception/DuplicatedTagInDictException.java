package ovh.major.mybackendapp.domain.blog.infrastructure.exception;

public class DuplicatedTagInDictException extends RuntimeException {

    public DuplicatedTagInDictException() {
        super("ERROR: Attemption of adding duplicated Tag to dictionary");
    }
}
