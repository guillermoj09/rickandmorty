package cl.mobdev.rickandmorty.mocks;

import cl.mobdev.rickandmorty.TestUtil;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public class CharacterEntityMock {

  public static List<CharacterEntity> build() {
    String jsonFile = "characterEntities.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<List<CharacterEntity>>() {
    });
  }

  public static List<CharacterEntity> build_with_ids_null() {
    String jsonFile = "characterEntitiesWithIdsNull.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<List<CharacterEntity>>() {
    });
  }

  public static CharacterEntity build_only() {
    String jsonFile = "characterEntityWithIdNull.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<CharacterEntity>() {
    });
  }

  public static CharacterEntity build_only_with_location_null() {
    String jsonFile = "characterEntityWithLocationNull.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<CharacterEntity>() {
    });
  }

  //listCharactersWithLocationOriginNull.json

  public static List<CharacterEntity> listCharactersWithOriginAndLocationNullImpl() {
    String jsonFile = "listCharactersEntitiesWithLocationOriginNullImpl.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<List<CharacterEntity>>() {
    });
  }

  public static List<CharacterEntity> listCharactersSuccess() {
    String jsonFile = "listCharactersEntitiesWithLocationOriginNullImpl.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<List<CharacterEntity>>() {
    });
  }

}
