package cl.mobdev.rickandmorty.infraestructure.controller.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.mocks.ApiCharacterMock;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApiCharacterToCharacterMapperTest {

  private ApiCharacterToCharacterMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new ApiCharacterToCharacterMapper();
  }

  @Test
  void should_return_success_character_mapper() {
    Character characterExpected = CharacterMock.build_character_api();

    //GIVEN
    ApiCharacter apiCharacter = ApiCharacterMock.build_character_api();
    //WHEN
    Character characterMapper = mapper.mapFrom(apiCharacter);
    //THEN
    assertEquals(characterExpected.getName(), characterMapper.getName());
    assertEquals(characterExpected.getStatus(), characterMapper.getStatus());
    assertEquals(characterExpected.getSpecies(), characterMapper.getSpecies());
    assertEquals(characterExpected.getType(), characterMapper.getType());
    assertNotNull(characterExpected.getLocation());
    assertNotNull(characterExpected.getOrigin());
  }

  @Test
  void should_return_null_location_when_apiCharacter_location_is_null() {
    Character characterExpected = CharacterMock.build_location_null_mapper();
    //GIVEN
    ApiCharacter apiCharacter = ApiCharacterMock.build_location_null_mapper();
    //WHEN
    Character characterMapper = mapper.mapFrom(apiCharacter);
    //THEN
    assertEquals(characterExpected.getLocation(),characterMapper.getLocation());
  }

  @Test
  void should_return_null_origin_when_apiCharacter_origin_is_null() {
    Character characterExpected = CharacterMock.build_origin_null_mapper();
    //GIVEN
    ApiCharacter apiCharacter = ApiCharacterMock.build_origin_null_mapper();
    //WHEN
    Character characterMapper = mapper.mapFrom(apiCharacter);
    //THEN
    assertEquals(characterExpected.getOrigin(),characterMapper.getOrigin());
  }


}