package cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.locations.entity.LocationEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.origin.entity.OriginEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CharacterCollectionsToEntityListMapper {

  public List<CharacterEntity> mapFrom(Collection<Character> characterCollection) {
    List<CharacterEntity> characterEntityList = new ArrayList<>();

    for (Character c : characterCollection) {
      CharacterEntity entity = new CharacterEntity();
      entity.setName(c.getName());
      characterEntityList.add(entity);
    }

    return characterEntityList;
  }

  public List<CharacterEntity> mapFromList(Collection<Character> characterCollection) {
    List<CharacterEntity> characterEntityList = new ArrayList<>();

    for (Character c : characterCollection) {
      CharacterEntity entity = new CharacterEntity();
      entity.setName(c.getName());
      entity.setStatus(c.getStatus());
      entity.setGender(c.getGender());
      entity.setType(c.getType());
      entity.setSpecies(c.getSpecies());
      entity.setLocation(locationToLocationEntity(c.getLocation()));
      entity.setOrigin(locationToOriginEntity(c.getOrigin()));
      characterEntityList.add(entity);

    }

    return characterEntityList;
  }

  private LocationEntity locationToLocationEntity(Location location) {
    if (location != null) {
      LocationEntity locationEntity = new LocationEntity();
      locationEntity.setName(location.getName());
      locationEntity.setType(location.getType());
      locationEntity.setDimension(location.getDimension());
      return locationEntity;
    }
    return null;

  }

  private OriginEntity locationToOriginEntity(Location origin) {
    if (origin != null) {
      OriginEntity originEntity = new OriginEntity();
      originEntity.setName(origin.getName());
      originEntity.setType(origin.getType());
      originEntity.setDimension(origin.getDimension());
      return originEntity;
    }
    return null;

  }


}
