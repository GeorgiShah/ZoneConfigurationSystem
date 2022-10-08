package am.system.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ZoneExistException extends RuntimeException {

    private String body;

    private HttpStatus httpStatus;
}
