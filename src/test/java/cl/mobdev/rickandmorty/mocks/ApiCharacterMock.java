package cl.mobdev.rickandmorty.mocks;

import cl.mobdev.rickandmorty.TestUtil;
import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import cl.mobdev.rickandmorty.domain.model.Character;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public class ApiCharacterMock {

  public static List<ApiCharacter> build() {
    String jsonFile = "apiCharacters.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<List<ApiCharacter>>() {
    });
  }

  public static ApiCharacter build_character_api() {
    String jsonFile = "characterApi.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<ApiCharacter>() {
    });
  }

  public static ApiCharacter build_location_null_mapper() {
    String jsonFile = "character_with_location_null_mapper.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<ApiCharacter>() {
    });
  }

  public static ApiCharacter build_origin_null_mapper() {
    String jsonFile = "character_with_origin_null_mapper.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<ApiCharacter>() {
    });
  }

}
