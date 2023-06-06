package cl.mobdev.rickandmorty.infraestructure.controller.mapper;

import static org.apache.coyote.http11.Constants.a;
import static org.junit.jupiter.api.Assertions.*;

import cl.mobdev.rickandmorty.api.model.ApiCharacterDb;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.mocks.ApiCharacterDbMock;
import cl.mobdev.rickandmorty.mocks.CharacterMock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharacterCollectionToApiCharacterDbMapperTest {

  private CharacterCollectionToApiCharacterDbMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new CharacterCollectionToApiCharacterDbMapper();
  }

  @Test
  void should_return_list_success_map() {

    List<ApiCharacterDb> characterDbsExpected = ApiCharacterDbMock.build();

    //GIVEN
    List<Character> characters = CharacterMock.listCharacters();

    List<ApiCharacterDb> characterDbs = mapper.mappingFrom(characters);

    for (ApiCharacterDb apiCharacterDb : characterDbs) {
      assertTrue(
          characterDbsExpected.stream().anyMatch(
              a -> a.getName().equals(apiCharacterDb.getName())
                  //&& a.getId().equals(apiCharacterDb.getId())
                  && a.getStatus().equals(apiCharacterDb.getStatus())
                  && a.getSpecies().equals(apiCharacterDb.getSpecies())
                  //&& a.getLocation().equals(apiCharacterDb.getLocation())
          )
      );
    }
  }

  @Test
  void should_return_list_success_but_with_origin_and_location_null() {

    List<ApiCharacterDb> characterDbsExpected = ApiCharacterDbMock.build_null();

    //GIVEN
    List<Character> characters = CharacterMock.listCharactersWithOriginAndLocationNull();

    List<ApiCharacterDb> characterDbs = mapper.mappingFrom(characters);

    for (ApiCharacterDb apiCharacterDb : characterDbs) {
      assertTrue(
          characterDbsExpected.stream().anyMatch(
              a -> a.getName().equals(apiCharacterDb.getName())
                  && a.getId().equals(apiCharacterDb.getId())
                  && a.getStatus().equals(apiCharacterDb.getStatus())
                  && a.getSpecies().equals(apiCharacterDb.getSpecies())

          )
      );
    }
  }


}