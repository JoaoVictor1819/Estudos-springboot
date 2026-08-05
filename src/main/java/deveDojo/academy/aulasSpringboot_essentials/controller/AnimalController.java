package deveDojo.academy.aulasSpringboot_essentials.controller;


import deveDojo.academy.aulasSpringboot_essentials.domain.Animais;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import deveDojo.academy.aulasSpringboot_essentials.service.AnimaisService;

import java.util.List;

@RestController
@RequestMapping("/animais")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimaisService animaisService;

    //localhost:8080/animais
    @GetMapping
    public ResponseEntity <List<Animais>> list() {
        return  ResponseEntity.ok(animaisService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Animais> findById(@PathVariable long id) {
        return  ResponseEntity.ok(animaisService.findByid(id));
    }



    @PostMapping
    public ResponseEntity<Animais> save(@RequestBody Animais animal){
      return new ResponseEntity<>(animaisService.save(animal), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animaisService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Animais animal) {
        animaisService.replace(animal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
