package deveDojo.academy.aulasSpringboot_essentials.handler;

import deveDojo.academy.aulasSpringboot_essentials.exception.BadRequestExcptionDetails;
import deveDojo.academy.aulasSpringboot_essentials.exception.BadRequestExcptions;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExcpitonHandler {

    @ExceptionHandler(BadRequestExcptions.class)
    public ResponseEntity<BadRequestExcptionDetails> handlerBadRequestExceptions(BadRequestExcptions bre){
        return new ResponseEntity<>(
                BadRequestExcptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Chek the Documetation")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
