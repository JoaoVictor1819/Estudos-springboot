package deveDojo.academy.aulasSpringboot_essentials.service;


import deveDojo.academy.aulasSpringboot_essentials.domain.Animais;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class AnimaisService {
    private static List<Animais> animais;

    static {
        animais = new ArrayList<>(List.of(new Animais(1L,"Cachorro"), new Animais(2L,"gato")));
    }

    // private final AnimaisRepository animaisRepository;
    public List<Animais> listAll(){
        return animais;
    }

    public Animais findByid(long id){
        return  animais.stream()
                .filter(animais -> animais.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Animais not Found"));

    }

    public Animais save(Animais animal){
        animal.setId(ThreadLocalRandom.current().nextLong(3, 100000));
        animais.add(animal);
        return animal;
    }

    public void delete(long id) {
        animais.remove(findByid(id));
    }

    public void replace(Animais animal) {
        delete(animal.getId());
        animais.add(animal);
    }
}
