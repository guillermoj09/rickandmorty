package cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.mocks.CharacterEntityMock;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharacterToCharacterEntityMapperTest {

  private CharacterToCharacterEntityMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new CharacterToCharacterEntityMapper();
  }

  @Test
  void should_verify_mapper_ok() {
    CharacterEntity characterEntityExpected = CharacterEntityMock.build_only();

    //GIVEN
    Character character = CharacterMock.build_compare_entity();
    //WHEN
    CharacterEntity characterEntity = mapper.mapFrom(character);
    //THEN
    assertEquals(characterEntityExpected.getName(), characterEntity.getName());
    assertEquals(characterEntityExpected.getSpecies(), characterEntity.getSpecies());
    assertEquals(characterEntityExpected.getStatus(), characterEntity.getStatus());
    assertEquals(characterEntityExpected.getType(), characterEntity.getType());

  }

  @Test
  void should_return_null_when_location_is_null() {
    CharacterEntity characterEntityExpected = CharacterEntityMock.build_only_with_location_null();

    //GIVEN
    Character character = CharacterMock.build_location_null_mapper();
    //WHEN
    CharacterEntity characterEntity = mapper.mapFrom(character);
    System.out.println(characterEntity.getLocation());
    //THEN
    assertEquals(characterEntityExpected.getLocation(),characterEntity.getLocation());

  }
}