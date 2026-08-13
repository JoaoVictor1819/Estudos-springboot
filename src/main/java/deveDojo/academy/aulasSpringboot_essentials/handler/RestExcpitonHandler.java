package deveDojo.academy.aulasSpringboot_essentials.handler;

import deveDojo.academy.aulasSpringboot_essentials.exception.BadRequestExcptionDetails;
import deveDojo.academy.aulasSpringboot_essentials.exception.BadRequestExcptions;
import deveDojo.academy.aulasSpringboot_essentials.exception.ExceptionsDetails;
import deveDojo.academy.aulasSpringboot_essentials.exception.ValidationExceptionsDatails;
import lombok.extern.log4j.Log4j2;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExcpitonHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestExcptions.class)
    public ResponseEntity<BadRequestExcptionDetails> handleBadRequestExceptions(BadRequestExcptions bre){
        return new ResponseEntity<>(
                BadRequestExcptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Chek the Documetation")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();

        String fields = fieldError.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMenssage = fieldError.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));


        return new ResponseEntity<>(
                ValidationExceptionsDatails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Invalid Fields")
                        .details("Check the field(s) error ")
                        .developerMessage(ex.getClass().getName())
                        .fields(fields)
                        .fieldsMenssage(fieldsMenssage)
                        .build(), HttpStatus.BAD_REQUEST);
    }




    @Override
    protected @Nullable ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        ExceptionsDetails exceptionsDetails = ExceptionsDetails.builder().build();
        BadRequestExcptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(statusCode.value())
                .title(ex.getCause().getMessage())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return createResponseEntity(exceptionsDetails, headers, statusCode, request);
    }
}
