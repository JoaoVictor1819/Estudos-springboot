package deveDojo.academy.aulasSpringboot_essentials.exception;


import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionsDatails extends ExceptionsDetails{
    private final String fields;
    private final String fieldsMenssage;
}
