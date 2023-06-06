package cl.mobdev.rickandmorty.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.SaveCharacterRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa.CharacterEntityRepository;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveCharacterInRepositoryUseCaseTest {

  @Mock
  private SaveCharacterRepository saveCharacterRepository;

  private SaveCharacterInRepositoryUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new SaveCharacterInRepositoryUseCase(saveCharacterRepository);
  }

  @Test
  void should_use_repository_and_save_a_character() {
    final int numberOfInvocationsExpected = 1;

    //GIVEN
    Character character = CharacterMock.build_insert_character();
    //WHEN
    useCase.save(character);
    //THEN
    verify(saveCharacterRepository,times(numberOfInvocationsExpected)).save(character);

  }
  // validaciones del largo de los campos

}