package cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.mocks.CharacterEntityMock;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharacterCollectionsToEntityListMapperTest {

  private CharacterCollectionsToEntityListMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new CharacterCollectionsToEntityListMapper();
  }

  @Test
  void should_return_list_of_entities() {
    List<CharacterEntity> expected = CharacterEntityMock.build_with_ids_null();

    //GIVEN
    Collection<Character> oneCollectionOfCharacters = CharacterMock.build_characters_with_origins_and_locations();

    //WHEN
    List<CharacterEntity> response = mapper.mapFrom(oneCollectionOfCharacters);

    //THEN
    for (CharacterEntity characterEntity : response) {
      assertTrue(
          expected.stream().anyMatch(
              a -> a.getName().equals(characterEntity.getName()) && null == characterEntity.getUuid()
          )
      );
    }

  }

  @Test
  void should_return_empty_list_when_input_collections_is_empty() {
    int sizeExpected = 0;

    //GIVEN
    Collection<Character> oneEmptyCollectionOfCharacters = new ArrayList<>();

    //WHEN
    List<CharacterEntity> response = mapper.mapFrom(oneEmptyCollectionOfCharacters);

    //THEN
    assertEquals(sizeExpected, response.size());
  }

}