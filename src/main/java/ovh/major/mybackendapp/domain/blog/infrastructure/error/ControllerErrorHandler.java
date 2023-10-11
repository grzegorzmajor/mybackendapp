package ovh.major.mybackendapp.domain.blog.infrastructure.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.DependencyExistsException;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.DuplicatedTagInDictException;
import ovh.major.mybackendapp.domain.blog.infrastructure.exception.NoUsedTagInDictDBException;

@ControllerAdvice
class ControllerErrorHandler {

    private static final String NO_TAG_IN_DICT = "Something goes wrong! Maybe there is no used tag in dictionary.";
    private static final String DUPLICATED_TAG = "Something goes wrong! Tag already exist in dictionary.";
    private static final String DELETE_NOT_POSSIBLE_BECAUSE_DEPENDENCY_IS_EXIST = "Something goes wrong! Deleting not possible because dependency is exist.";

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

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DependencyExistsException.class)
    @ResponseBody
    public ErrorResponse handleDependencyExistException() {
        return new ErrorResponse(DELETE_NOT_POSSIBLE_BECAUSE_DEPENDENCY_IS_EXIST,HttpStatus.CONFLICT);
    }

}
