package cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.locations.entity.LocationEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.origin.entity.OriginEntity;
import org.springframework.stereotype.Component;

@Component
public class CharacterToCharacterEntityMapper {

  public CharacterEntity mapFrom(Character character) {
    CharacterEntity characterEntity = new CharacterEntity();
    characterEntity.setName(character.getName());
    characterEntity.setStatus(character.getStatus());
    characterEntity.setType(character.getType());
    characterEntity.setSpecies(character.getSpecies());
    characterEntity.setLocation(locationToEntity(character.getLocation()));
    characterEntity.setOrigin(originToEntity(character.getOrigin()));
    return characterEntity;
  }

  private LocationEntity locationToEntity(Location location) {
    LocationEntity locationEntity = new LocationEntity();
    if (location != null) {
      locationEntity.setName(location.getName());
      locationEntity.setDimension(location.getDimension());
      locationEntity.setType(location.getType());
      return locationEntity;
    }
    return null;
  }

  private OriginEntity originToEntity(Location location) {
    OriginEntity originEntity = new OriginEntity();
    if (location != null) {
      originEntity.setName(location.getName());
      originEntity.setDimension(location.getDimension());
      originEntity.setType(location.getType());
      return originEntity;
    }
    return null;
  }


}
