package deveDojo.academy.aulasSpringboot_essentials.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExcptions extends RuntimeException{
    public BadRequestExcptions(String message) {
        super(message);
    }
}
