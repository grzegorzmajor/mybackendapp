package ovh.major.mybackendapp.domain.blog.infrastructure.exception;

public class NothingToChangeException extends RuntimeException {

    public NothingToChangeException() {
        super("ERROR: Theres nothing to updatate!");
    }
}
