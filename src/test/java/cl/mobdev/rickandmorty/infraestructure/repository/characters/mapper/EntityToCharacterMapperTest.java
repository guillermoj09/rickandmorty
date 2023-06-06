package cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.mocks.CharacterEntityMock;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class EntityToCharacterMapperTest {

  private EntityToCharacterMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new EntityToCharacterMapper();
  }

  @Test
  void should_return_Character_from_entity() {
    Character characterExpected = CharacterMock.build_compare_entity();

    //GIVEN
    CharacterEntity characterEntity = CharacterEntityMock.build_only();
    Character character = mapper.mapFrom(characterEntity);
    //THEN
    assertEquals(characterExpected.getName(), character.getName());
    assertEquals(characterExpected.getSpecies(), character.getSpecies());
    assertEquals(characterExpected.getStatus(), character.getStatus());
    assertEquals(characterExpected.getType(), character.getType());


  }

  @Test
  void should_return_null_character_when_is_entity() {
    Character characterExpected = null;

    //GIVEN
    CharacterEntity characterEntity = null;
    Character character = mapper.mapFrom(characterEntity);

    //THEN
    assertEquals(characterExpected, character);


  }

}