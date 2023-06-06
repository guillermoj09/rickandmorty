package cl.mobdev.rickandmorty.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.input.GetAllCharactersInRepository;
import cl.mobdev.rickandmorty.domain.port.output.GetAllCharactersRepository;
import cl.mobdev.rickandmorty.mocks.CharacterMock;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllCharactersInRepositoryUseCaseTest {

  private GetAllCharactersInRepositoryUseCase useCase;

  @Mock
  private GetAllCharactersRepository getAllCharactersRepository;


  @BeforeEach
  void setUp() {
    useCase = new GetAllCharactersInRepositoryUseCase(getAllCharactersRepository);
  }

  @Test
  void should_return_characters_when_execute_findAll() {

    int numberOfInvocationsExpected = 1;

    Collection<Character> characters = CharacterMock.listCharactersWithOriginAndLocationNull();
    //GIVEN
    when(getAllCharactersRepository.findAll()).thenReturn(characters);
    //WHEN
    useCase.findAll();
    //THEN
    verify(getAllCharactersRepository, times(numberOfInvocationsExpected)).findAll();

  }

}