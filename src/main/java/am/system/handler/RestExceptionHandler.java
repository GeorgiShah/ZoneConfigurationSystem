package am.system.handler;

import am.system.dto.ErrorMessageDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collection;
import java.util.LinkedList;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        if (ex instanceof ZoneExistException) {
            HttpStatus httpStatus = ((ZoneExistException) ex).getHttpStatus();
            String body = ((ZoneExistException) ex).getBody();

            return new ResponseEntity<>(new ErrorMessageDTO(body), httpStatus);
        }

        return handleExceptionInternal(ex, "Something went wrong", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Collection<ErrorMessageDTO> errorMessageDTOS = new LinkedList<>();

        ex.getBindingResult().getAllErrors().forEach(objectError -> errorMessageDTOS.add(new ErrorMessageDTO(((FieldError) objectError).getField(), objectError.getDefaultMessage())));

        return new ResponseEntity<>(errorMessageDTOS, HttpStatus.CONFLICT);
    }
}
