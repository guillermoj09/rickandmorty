package cl.mobdev.rickandmorty.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.GetCharacterGateway;
import cl.mobdev.rickandmorty.domain.usecase.GetCharacterIsValidHumanUseCase2;
import cl.mobdev.rickandmorty.mocks.CharacterMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith({MockitoExtension.class})
class GetCharacterIsValidHumanUseCase2Test {

  private GetCharacterIsValidHumanUseCase2 useCase;

  @Mock
  private GetCharacterGateway gateway;

  @BeforeEach
  public void setUp() {
    useCase = new GetCharacterIsValidHumanUseCase2(gateway);
  }


  @Test
  void should_return_true_when_is_human() {

    boolean expected = true;

    //Given
    final int idCharacter = 1;
    Character characterMock = CharacterMock.build_character_only();

    when(gateway.execute(idCharacter)).thenReturn(characterMock);
    //When
    boolean valid = useCase.execute(1);
    //THEN
    assertEquals(expected,valid);

  }

  @Test
  void should_return_false_when_is_not_human() {

    boolean expected = false;

    //Given
    final int idCharacter = 6;
    Character characterMock = CharacterMock.build_character_specie_alien();

    when(gateway.execute(idCharacter)).thenReturn(characterMock);
    //When
    boolean valid = useCase.execute(6);
    //THEN
    assertEquals(expected,valid);

  }


}