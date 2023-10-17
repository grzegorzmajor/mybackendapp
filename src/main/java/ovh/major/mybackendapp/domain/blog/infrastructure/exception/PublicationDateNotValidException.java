package ovh.major.mybackendapp.domain.blog.infrastructure.exception;

public class PublicationDateNotValidException extends RuntimeException {
    public PublicationDateNotValidException() {
        super("ERROR: Publication date must be not older than 1 hour!");
    }
}
