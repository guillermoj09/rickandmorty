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

class EntityListToCharacterCollectionsMapperTest {

  private EntityListToCharacterCollectionsMapper mapper;

  @BeforeEach
  void setUp() {
    this.mapper = new EntityListToCharacterCollectionsMapper();
  }

  @Test
  void should_return_list_of_characters() {
    Collection<Character> expected = CharacterMock.build_from_repository();

    //GIVEN
    List<CharacterEntity> oneListOfCharacterEntities = CharacterEntityMock.build_with_ids_null();

    //WHEN
    Collection<Character> response = mapper.mapFrom(oneListOfCharacterEntities);

    //THEN
    for (Character character : response) {
      assertTrue(
          expected.stream().anyMatch(
              a -> a.getName().equals(character.getName())
                  && null == character.getId()
                  && null == character.getStatus()
                  && null == character.getSpecies()
                  && null == character.getType()
                  && null == character.getGender()
                  && null == character.getOrigin()
                  && null == character.getLocation()
          )
      );
    }

  }

  @Test
  void should_return_empty_list_when_input_collections_is_empty() {
    int sizeExpected = 0;

    //GIVEN
    List<CharacterEntity> oneEmptyListOfCharacterEntities = new ArrayList<>();
    //WHEN
    Collection<Character> response = mapper.mapFrom(oneEmptyListOfCharacterEntities);
    //THEN
    assertEquals(sizeExpected, response.size());
  }

  @Test
  void should_return_mapFromList_success(){

    Collection<Character> expected = CharacterMock.listCharacters();



    List<CharacterEntity> characterEntities = CharacterEntityMock.listCharactersSuccess();

    Collection<Character> listCharacters = mapper.mapFromList(characterEntities);

    for (Character character : listCharacters) {
      assertTrue(
          expected.stream().anyMatch(
              a -> a.getName().equals(character.getName())

          )
      );
    }

  }

}