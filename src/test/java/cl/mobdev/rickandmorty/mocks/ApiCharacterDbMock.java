package cl.mobdev.rickandmorty.mocks;

import cl.mobdev.rickandmorty.TestUtil;
import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import cl.mobdev.rickandmorty.api.model.ApiCharacterDb;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class ApiCharacterDbMock {

  public static List<ApiCharacterDb> build() {
    String jsonFile = "listCharacterEntity.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<List<ApiCharacterDb>>() {
    });
  }

  public static List<ApiCharacterDb> build_null() {
    String jsonFile = "listCharactersEntityWithLocationOriginNull.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<List<ApiCharacterDb>>() {
    });
  }
}
