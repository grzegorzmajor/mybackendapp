package ovh.major.mybackendapp.domain.blog.infrastructure.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.DuplicatedTagInDictException;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.EntityWithIdNotExistException;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.NoUsedTagInDictDBException;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.NothingToChangeException;

import java.util.NoSuchElementException;

@ControllerAdvice
class ControllerErrorHandler {

    private static final String NO_TAG_IN_DICT = "Something goes wrong! Maybe there is no used tag in dictionary.";
    private static final String DUPLICATED_TAG = "Something goes wrong! Tag already exist in dictionary.";
    private static final String NOTHING_TO_CHANGE = "Nothing to change! But everything it`s ok!";

    private static final String ENTITY_NOT_EXIST =  "Entity with specified ID nod exist!";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoUsedTagInDictDBException.class)
    @ResponseBody
    public ErrorResponse handleUsedTagInDictDBException() {
        return new ErrorResponse(NO_TAG_IN_DICT, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicatedTagInDictException.class)
    @ResponseBody
    public ErrorResponse handleDuplicatedTagInDictException() {
        return new ErrorResponse(DUPLICATED_TAG, HttpStatus.CONFLICT);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NothingToChangeException.class)
    @ResponseBody
    public ErrorResponse handleNothingToChangeException() {
        return new ErrorResponse(NOTHING_TO_CHANGE, HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EntityWithIdNotExistException.class, NoSuchElementException.class})
    @ResponseBody
    public ErrorResponse handleEntityNotExistException() {
        return new ErrorResponse(ENTITY_NOT_EXIST, HttpStatus.BAD_REQUEST);
    }
}
