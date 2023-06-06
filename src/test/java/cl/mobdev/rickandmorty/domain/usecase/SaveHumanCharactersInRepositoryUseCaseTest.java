package cl.mobdev.rickandmorty.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.SaveCharactersRepository;
import cl.mobdev.rickandmorty.domain.usecase.SaveHumanCharactersInRepositoryUseCase;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveHumanCharactersInRepositoryUseCaseTest {

  private SaveHumanCharactersInRepositoryUseCase useCase;

  @Mock
  private SaveCharactersRepository saveCharactersRepository;

  @Captor
  private ArgumentCaptor<Collection<Character>> characterCaptor;

  @BeforeEach
  public void setUp() {
    useCase = new SaveHumanCharactersInRepositoryUseCase(saveCharactersRepository);
  }

  @Test
  void should_save_in_repository_only_human_characters() {
    int sizeExpected = 15;
    int numberOfInvocationsExpected = 1;
    String specieExpected = "Human";

    //GIVEN
    Collection<Character> oneCollectionOfCharacters = CharacterMock.build_characters_with_origins_and_locations();

    //WHEN
    useCase.execute(oneCollectionOfCharacters);

    //THEN
    verify(saveCharactersRepository, times(numberOfInvocationsExpected)).save(characterCaptor.capture());
    assertEquals(sizeExpected, characterCaptor.getValue().size());
    for (Character c : characterCaptor.getValue()) {
      assertEquals(specieExpected, c.getSpecies());
    }
  }

}
