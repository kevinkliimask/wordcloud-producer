package kevink.producer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidFileException extends ResponseStatusException {

    public InvalidFileException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
