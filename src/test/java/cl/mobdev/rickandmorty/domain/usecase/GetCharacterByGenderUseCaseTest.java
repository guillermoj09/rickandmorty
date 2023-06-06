package cl.mobdev.rickandmorty.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.GetCharacterGateway;
import cl.mobdev.rickandmorty.domain.usecase.GetCharacterByGenderUseCase;
import cl.mobdev.rickandmorty.infraestructure.exception.GenderException;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class GetCharacterByGenderUseCaseTest {

  private GetCharacterByGenderUseCase useCase;

  @Mock
  private GetCharacterGateway getCharacterGateway;

  @BeforeEach
  public void setUp() {
    useCase = new GetCharacterByGenderUseCase(getCharacterGateway);
  }

  @Test
  void should_return_character_when_is_female() {
    String expectedFemale = "Female";
    //Given
    final int idCharacter = 6;
    Character characterMock = CharacterMock.build_character_specie_alien();
    when(getCharacterGateway.execute(idCharacter)).thenReturn(characterMock);
    //WHEN
    Character character = useCase.execute(6);
    //THEN
    assertEquals(expectedFemale, character.getGender());

  }

  @Test
  void should_return_character_when_is_not_female() {
    String expectedFemale = "El character no es mujer";
    //Given
    final int idCharacter = 1;
    Character characterMock = CharacterMock.build_character_only();
    when(getCharacterGateway.execute(idCharacter)).thenReturn(characterMock);
    //WHEN
    GenderException thrown = assertThrows(
        GenderException.class,
        () -> useCase.execute(1)
    );
    //THEN
    assertEquals(expectedFemale,thrown.getMessage());

  }

}
