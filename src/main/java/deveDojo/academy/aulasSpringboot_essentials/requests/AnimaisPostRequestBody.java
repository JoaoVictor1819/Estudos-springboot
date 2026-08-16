package deveDojo.academy.aulasSpringboot_essentials.requests;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;


@Data
public class AnimaisPostRequestBody {
    @NotEmpty(message = "The animais name cannot be empty")
    private String name;



}
