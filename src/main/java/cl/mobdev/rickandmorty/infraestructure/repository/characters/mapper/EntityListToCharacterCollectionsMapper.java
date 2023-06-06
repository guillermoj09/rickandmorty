package cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.locations.entity.LocationEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.origin.entity.OriginEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class EntityListToCharacterCollectionsMapper {

  public Collection<Character> mapFrom(List<CharacterEntity> characterCollection) {
    List<Character> characterList = new ArrayList<>();

    for (CharacterEntity entity : characterCollection) {
      Character character = Character.builder().withName(entity.getName()).build();
      characterList.add(character);
    }

    return characterList;
  }

  public Collection<Character> mapFromList(List<CharacterEntity> characterCollection) {
    List<Character> characterList = new ArrayList<>();

    for (CharacterEntity entity : characterCollection) {

      Character character = Character.builder()
          .withUuid(entity.getUuid().toString())
          .withName(entity.getName())
          .withType(entity.getType())
          .withStatus(entity.getStatus())
          .withSpecies(entity.getSpecies())
          .withOrigin(originEntityToOriginMapper(entity.getOrigin()))
          .withLocation(locationEntityToLocationMapper(entity.getLocation()))
          .build();
      characterList.add(character);
    }
    return characterList;
  }

  private Location originEntityToOriginMapper(OriginEntity originEntity) {

    return originEntity == null ? null : Location.builder()
        .withUuid(String.valueOf(originEntity.getId()))
        .withName(originEntity.getName())
        .withType(originEntity.getType())
        .withDimension(originEntity.getDimension())
        .build();

  }

  private Location locationEntityToLocationMapper(LocationEntity locationEntity) {
    return locationEntity == null ? null : Location.builder()
        .withUuid(String.valueOf(locationEntity.getId()))
        .withName(locationEntity.getName())
        .withType(locationEntity.getType())
        .withDimension(locationEntity.getDimension())
        .build();

  }

}
