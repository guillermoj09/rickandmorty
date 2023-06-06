package cl.mobdev.rickandmorty.domain.usecase;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.SaveCharactersRepository;
import cl.mobdev.rickandmorty.domain.usecase.SaveAllCharactersInRepositoryUseCase;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveAllCharactersInRepositoryUseCaseTest {

  private SaveAllCharactersInRepositoryUseCase useCase;

  @Mock
  private SaveCharactersRepository saveCharactersRepository;

  @BeforeEach
  public void setUp() {
    useCase = new SaveAllCharactersInRepositoryUseCase(saveCharactersRepository);
  }

  @Test
  void should_use_repository_and_save_a_characters_collections() {
    int numberOfInvocationsExpected = 1;

    //GIVEN
    Collection<Character> oneCollectionOfCharacters = CharacterMock.build_characters_with_origins_and_locations();

    //WHEN
    useCase.save(oneCollectionOfCharacters);

    //THEN
    verify(saveCharactersRepository, times(numberOfInvocationsExpected)).save(oneCollectionOfCharacters);
  }

}