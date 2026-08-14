package deveDojo.academy.aulasSpringboot_essentials.client;


import deveDojo.academy.aulasSpringboot_essentials.domain.Animais;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    static void main(String[] args) {
        ResponseEntity<Animais> Entity = new RestTemplate().getForEntity("http://localhost:8080/animais/{id}", Animais.class, 2 );
        log.info(Entity);

        Animais Object = new RestTemplate().getForObject("http://localhost:8080/animais/{id}", Animais.class, 1);

        log.info(Object);

        Animais[] animais = new RestTemplate().getForObject("http://localhost:8080/animais/all", Animais[].class);

        log.info(Arrays.toString(animais));

        ResponseEntity<List<Animais>> exchange = new RestTemplate().exchange("http://localhost:8080/animais/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        log.info(exchange.getBody());


//        Animais Jabuti = Animais.builder().name("Jabuti").build();
//        Animais JabutiAnimais = new RestTemplate().postForObject("http://localhost:8080/animais/", Jabuti, Animais.class);
//        log.info("saved animais {}", JabutiAnimais);

        Animais Carcara = Animais.builder().name("Carcara").build();
        ResponseEntity<Animais> CarcaraSaved = new RestTemplate().exchange("http://localhost:8080/animais/",
                HttpMethod.POST,
                new HttpEntity<>(Carcara, creatJson()),
                Animais.class);

        log.info("saved animais {}", CarcaraSaved);
    }
    private static HttpHeaders creatJson(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
