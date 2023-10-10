package ovh.major.mybackendapp.domain.blog.infrastructure.exception;

public class NoUsedTagInDictDBException extends RuntimeException {

    public NoUsedTagInDictDBException() {
        super("ERROR: While adding post user want used tag but it doesn't exist in dictionary (in DB)");
    }
}
