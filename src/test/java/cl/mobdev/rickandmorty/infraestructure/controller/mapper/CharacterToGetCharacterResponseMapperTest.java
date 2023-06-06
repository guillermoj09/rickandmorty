package cl.mobdev.rickandmorty.infraestructure.controller.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;

import cl.mobdev.rickandmorty.api.model.GetCharacterResponse;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import cl.mobdev.rickandmorty.mocks.GetCharacterResponseMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CharacterToGetCharacterResponseMapperTest {


  private CharacterToGetCharacterResponseMapper mapper;


  @BeforeEach
  void setUp() {
    this.mapper = new CharacterToGetCharacterResponseMapper();
  }

  @Test
  void should_return_a_response_when_receive_a_character_object() {
    GetCharacterResponse expected = GetCharacterResponseMock.build();

    //GIVEN
    Character objectTomapping = CharacterMock.build_character_with_location();

    GetCharacterResponse response = mapper.mappingFrom(objectTomapping);

    assertEquals(expected.getId(), response.getId());


  }

  @Test
  void should_return_a_response_with_origin_null_when_receive_a_character_object() {

    //GIVEN
    Character objectTomaping = CharacterMock.build_character_only_origin_empty();
    objectTomaping.setLocation(null);
    //WHEN
    GetCharacterResponse response = mapper.mappingFrom(objectTomaping);
    //THEN
    assertNull(response.getOrigin().getUrl());
  }

}
