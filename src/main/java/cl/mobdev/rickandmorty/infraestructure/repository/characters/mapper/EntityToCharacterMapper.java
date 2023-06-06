package cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityToCharacterMapper {

  public Character mapFrom(CharacterEntity characterEntity) {
    Character character = new Character();
    if (characterEntity != null) {
      character.setName(characterEntity.getName());
      character.setType(characterEntity.getType());
      character.setStatus(characterEntity.getStatus());
      character.setSpecies(characterEntity.getSpecies());
      return character;
    }
    return null;
  }
}
