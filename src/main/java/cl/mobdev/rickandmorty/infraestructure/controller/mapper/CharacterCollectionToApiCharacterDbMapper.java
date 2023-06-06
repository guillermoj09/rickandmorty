package cl.mobdev.rickandmorty.infraestructure.controller.mapper;

import cl.mobdev.rickandmorty.api.model.ApiCharacterDb;
import cl.mobdev.rickandmorty.api.model.ApiLocationDb;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CharacterCollectionToApiCharacterDbMapper {

  public List<ApiCharacterDb> mappingFrom(Collection<Character> characters) {
    List<ApiCharacterDb> characterDbs = new ArrayList<>();

    for (Character character : characters) {
      ApiCharacterDb apiCharacterDb = new ApiCharacterDb();
      apiCharacterDb.setId(character.getUuid());
      apiCharacterDb.setName(character.getName());
      apiCharacterDb.setStatus(character.getStatus());
      apiCharacterDb.setSpecies(character.getSpecies());
      apiCharacterDb.setType(character.getType());
      apiCharacterDb.setOrigin(originToApiLocation(character.getOrigin()));
      apiCharacterDb.setLocation(locationToApiLocation(character.getLocation()));
      characterDbs.add(apiCharacterDb);
    }

    return characterDbs;
  }

  private ApiLocationDb originToApiLocation(Location origin) {
    if (origin != null) {
      ApiLocationDb apiLocationDb = new ApiLocationDb();
      apiLocationDb.setId(origin.getUuid());
      apiLocationDb.setName(origin.getName());
      apiLocationDb.setDimension(origin.getDimension());
      apiLocationDb.setType(origin.getType());
      return apiLocationDb;
    }
    return null;
  }

  private ApiLocationDb locationToApiLocation(Location location) {
    if (location != null) {
      ApiLocationDb apiLocationDb = new ApiLocationDb();
      apiLocationDb.setId(location.getUuid());
      apiLocationDb.setName(location.getName());
      apiLocationDb.setDimension(location.getDimension());
      apiLocationDb.setType(location.getType());
      return apiLocationDb;
    }
    return null;

  }
}
