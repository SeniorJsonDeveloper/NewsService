package project.newtrying.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionProperties> handleNotFoundEx(NotFoundException e){
        return ResponseEntity.ok(
                new ExceptionProperties(HttpStatus.NO_CONTENT.value(),
                        e.getMessage(),HttpStatus.NOT_FOUND)
        );
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionProperties> handelBadReqEx(BadRequestException e){
        return ResponseEntity.ok(
                new ExceptionProperties(
                        HttpStatus.NO_CONTENT.value(),
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST
                )
        );
    }
}
