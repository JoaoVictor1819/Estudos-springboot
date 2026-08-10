package deveDojo.academy.aulasSpringboot_essentials.repository;

import deveDojo.academy.aulasSpringboot_essentials.domain.Animais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimaisRepository extends JpaRepository <Animais, Long>{

}
