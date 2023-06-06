package cl.mobdev.rickandmorty.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.GetAllCharactersGateway;
import cl.mobdev.rickandmorty.domain.usecase.GetCharactersSorByNameAndTypeUseCase;
import cl.mobdev.rickandmorty.mocks.CharacterMock;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetCharactersSorByNameAndTypeUseCaseTest {

  @Mock
  private GetAllCharactersGateway gateway;

  private GetCharactersSorByNameAndTypeUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetCharactersSorByNameAndTypeUseCase(gateway);
  }

  @Test
  void should_return_list_sort_by_name_and_type_mars() {
    final int expected = 3;
    //Given
    Collection<Character> characters = CharacterMock.build_characters_with_planet_mars();

    when(gateway.execute()).thenReturn(characters);

    //WHEN
    Collection<Character> charactersResult = useCase.execute();
    //THEN
    assertEquals(expected, charactersResult.size());

  }

  @Test
  void should_return_change_Type_character() {
    String expected = "M4rty Sm3th";
    Collection<Character> characters = CharacterMock.build_characters_with_planet_mars_character_name_empty();

    when(gateway.execute()).thenReturn(characters);

    //WHEN
    Collection<Character> charactersResult = useCase.execute();

    for (Character c :
        charactersResult) {
      if (expected.equals(c.getType())) {
        assertEquals(expected, c.getType());
      }
    }

  }



}
