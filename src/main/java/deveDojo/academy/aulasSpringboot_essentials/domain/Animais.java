package deveDojo.academy.aulasSpringboot_essentials.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class Animais  {
    private Long id;
    private String nome;



}
