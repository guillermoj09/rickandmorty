package cl.mobdev.rickandmorty.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.GetCharacterGateway;
import cl.mobdev.rickandmorty.domain.port.output.GetLocationGateway;
import cl.mobdev.rickandmorty.domain.usecase.GetCharacterByIdUseCase;
import cl.mobdev.rickandmorty.mocks.CharacterMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class GetCharacterByIdUseCaseTest {

  private GetCharacterByIdUseCase useCase;

  @Mock
  private GetCharacterGateway getCharacterGateway;

  @Mock
  private GetLocationGateway getLocationGateway;


  @BeforeEach
  void setUp() {
    useCase = new GetCharacterByIdUseCase(getCharacterGateway, getLocationGateway);
  }

  @Test
  void should_return_character_only_object_by_id() {

    final int idExpected = 1;

    //Given
    final int idCharacter = 1;
    Character characterMock = CharacterMock.build_character_only();
    when(getCharacterGateway.execute(idCharacter)).thenReturn(characterMock);

    //when
    Character character = useCase.execute(1);

    //then
    assertEquals(idExpected, character.getId());

  }

  @Test
  void should_return_character_without_origin_empty() {
    final String originUrlExpected = "";

    //Given
    final int idCharacter = 2;
    Character characterMock = CharacterMock.build_character_only_origin_empty();
    when(getCharacterGateway.execute(idCharacter)).thenReturn(characterMock);
    //when
    Character character = useCase.execute(2);
    //Then
    assertEquals(originUrlExpected, character.getOrigin().getUrl());

  }

}
