package cl.mobdev.rickandmorty.domain.usecase;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import cl.mobdev.rickandmorty.domain.port.output.DeleteCharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteCharacterInRepositoryUseCaseTest {
  @Mock
  private DeleteCharacterRepository deleteCharacterRepository;

  private DeleteCharacterInRepositoryUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteCharacterInRepositoryUseCase(deleteCharacterRepository);
  }

  @Test
  void should_use_repository_and_delete_character() {
    final int numberOfInvocationsExpected = 1;
    final String idCharacter = "Hak12ja1aa";
    //GIVEN
    //WHEN
    useCase.execute(idCharacter);
    //THEN
    verify(deleteCharacterRepository,times(numberOfInvocationsExpected)).delete(idCharacter);
  }

}