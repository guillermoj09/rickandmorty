package cl.mobdev.rickandmorty.infraestructure.controller.mapper;

import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import cl.mobdev.rickandmorty.domain.model.Character;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ApiCharacterCollectionsToCharacterCollectionsMapper {

  public Collection<Character> mapFrom(Collection<ApiCharacter> apiCharacters) {
    List<Character> characters = new ArrayList<>();

    for (ApiCharacter apiCharacter : apiCharacters) {
      characters.add(
          Character.builder()
          .withName(apiCharacter.getName())
          .build());
    }

    return characters;
  }
}
