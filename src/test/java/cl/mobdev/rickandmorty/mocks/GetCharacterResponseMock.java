package cl.mobdev.rickandmorty.mocks;

import cl.mobdev.rickandmorty.TestUtil;
import cl.mobdev.rickandmorty.api.model.GetCharacterResponse;
import com.fasterxml.jackson.core.type.TypeReference;

public class GetCharacterResponseMock {

  public static GetCharacterResponse build() {
    String jsonFile = "getCharacterResponse.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<GetCharacterResponse>() {
    });
  }

  public static GetCharacterResponse buildWithOriginNull() {
    String jsonFile = "getCharacterResponseWithoutOriginNull.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<GetCharacterResponse>() {
    });
  }

}
