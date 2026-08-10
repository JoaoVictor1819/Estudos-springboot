package deveDojo.academy.aulasSpringboot_essentials.mapper;

import deveDojo.academy.aulasSpringboot_essentials.domain.Animais;
import deveDojo.academy.aulasSpringboot_essentials.requests.AnimaisPostRequestBody;
import deveDojo.academy.aulasSpringboot_essentials.requests.AnimaisPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimaisMapper {
    public static final AnimaisMapper INSTANCE = Mappers.getMapper(AnimaisMapper.class);

    public abstract Animais toAnimais(AnimaisPostRequestBody animaisPostRequestBody);

    public abstract Animais toAnimais(AnimaisPutRequestBody animaisPutRequestBody);
}
