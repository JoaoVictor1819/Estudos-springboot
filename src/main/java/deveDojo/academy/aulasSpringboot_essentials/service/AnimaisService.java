package deveDojo.academy.aulasSpringboot_essentials.service;


import deveDojo.academy.aulasSpringboot_essentials.domain.Animais;
import deveDojo.academy.aulasSpringboot_essentials.mapper.AnimaisMapper;
import deveDojo.academy.aulasSpringboot_essentials.repository.AnimaisRepository;
import deveDojo.academy.aulasSpringboot_essentials.requests.AnimaisPostRequestBody;
import deveDojo.academy.aulasSpringboot_essentials.requests.AnimaisPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AnimaisService {


    private final AnimaisRepository animaisRepository;


    public List<Animais> listAll() {
        return animaisRepository.findAll();
    }

    public Animais findByidOrThrowBadRequestExecepition(long id) {
        return animaisRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Animais not Found"));

    }

    public Animais save(AnimaisPostRequestBody animaisPostRequestBody) {
        return animaisRepository.save(AnimaisMapper.INSTANCE.toAnimais(animaisPostRequestBody));
    }

    public void delete(long id) {
        animaisRepository.delete(findByidOrThrowBadRequestExecepition(id));
    }

    public void replace(AnimaisPutRequestBody animaisPutRequestBody) {
        Animais savedAnime = findByidOrThrowBadRequestExecepition(animaisPutRequestBody.getId());
        Animais animal = AnimaisMapper.INSTANCE.toAnimais(animaisPutRequestBody);
        animal.setId(savedAnime.getId());
        animaisRepository.save(animal);
    }
}
