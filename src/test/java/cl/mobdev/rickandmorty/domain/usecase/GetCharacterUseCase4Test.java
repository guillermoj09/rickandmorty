package cl.mobdev.rickandmorty.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cl.mobdev.rickandmorty.domain.model.Character;

import cl.mobdev.rickandmorty.domain.usecase.GetCharacterUseCase4;
import cl.mobdev.rickandmorty.infraestructure.exception.GenderException;
import cl.mobdev.rickandmorty.infraestructure.exception.LocationException;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetCharacterUseCase4Test {

  private GetCharacterUseCase4 useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetCharacterUseCase4();
  }

  @Test
  void should_return_true_when_character_is_match_origin() {
    //

    //GIVEN
    Character characterMock = CharacterMock.build_character_three();

    //WHEN
    boolean result = useCase.execute(characterMock);

    //THEN
    assertTrue(result);
  }

  @Test
  void should_return_throw_when_origin_and_location_are_unknown() {
    //
    String expected = "Origen y locacion desconocidos";

    //GIVEN
    Character characterMock = CharacterMock.build_character_with_origin_location_unknown();

    //WHEN
    LocationException thrown = assertThrows(
        LocationException.class,
        () -> useCase.execute(characterMock)
    );

    //THEN
    assertEquals(expected, thrown.getMessage());
  }

  @Test
  void should_return_throw_when_origin_is_unknown() {
    //
    String expected = "Origen desconocido";

    //GIVEN
    Character characterMock = CharacterMock.build_character_only_origin_empty();

    //WHEN
    LocationException thrown = assertThrows(
        LocationException.class,
        () -> useCase.execute(characterMock)
    );

    //THEN
    assertEquals(expected, thrown.getMessage());
  }

  @Test
  void should_return_throw_when_location_is_unknown() {
    //
    String expected = "Locacion desconocido";

    //GIVEN
    Character characterMock = CharacterMock.build_character_with_location_unknown();

    //WHEN
    LocationException thrown = assertThrows(
        LocationException.class,
        () -> useCase.execute(characterMock)
    );

    //THEN
    assertEquals(expected, thrown.getMessage());
  }

}