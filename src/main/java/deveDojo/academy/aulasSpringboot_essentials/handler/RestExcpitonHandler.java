package deveDojo.academy.aulasSpringboot_essentials.handler;

import deveDojo.academy.aulasSpringboot_essentials.exception.BadRequestExcptionDetails;
import deveDojo.academy.aulasSpringboot_essentials.exception.BadRequestExcptions;
import deveDojo.academy.aulasSpringboot_essentials.exception.ValidationExceptionsDatails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionsDatails>
    handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
        String fields = fieldError.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMenssage = fieldError.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));


        return new ResponseEntity<>(
                ValidationExceptionsDatails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Invalid Fields")
                        .details("Check the field(s) error ")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMenssage(fieldsMenssage)
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
