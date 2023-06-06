package cl.mobdev.rickandmorty.infraestructure.controller.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.mocks.ApiCharacterMock;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApiCharacterCollectionsToCharacterCollectionsMapperTest {

  private ApiCharacterCollectionsToCharacterCollectionsMapper mapper;

  @BeforeEach
  void setUp() {
    this.mapper = new ApiCharacterCollectionsToCharacterCollectionsMapper();
  }

  @Test
  void should_return_a_response_when_receive_a_character_collections() {
    Collection<Character> expected = CharacterMock.build_from_repository();

    //GIVEN
    List<ApiCharacter> oneListOfApiCharacters = ApiCharacterMock.build();

    //WHEN
    Collection<Character> response = mapper.mapFrom(oneListOfApiCharacters);

    //THEN
    for (Character character : response) {
      assertTrue(
          expected.stream().anyMatch(
              a -> a.getName().equals(character.getName()))
      );
    }
  }

}