package cl.mobdev.rickandmorty.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.usecase.TotalResidentsByLocationAlien;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TotalResidentsByLocationAlienTest {

  private TotalResidentsByLocationAlien useCase;

  @BeforeEach
  void setUp() {
    useCase = new TotalResidentsByLocationAlien();
  }

  @Test
  void should_return_total_five_the_location_residents_success() {
    //
    int expected = 5;

    //GIVEN
    Collection<Character> charactersMock = CharacterMock.build_characters_with_alien();

    //WHEN
    Integer result = useCase.execute(charactersMock);

    //THEN
    assertEquals(expected, result);
  }

  @Test
  void should_return_total_two_when_id_character_is_null() {
    //
    int expected = 2;

    //GIVEN
    Collection<Character> charactersMock = CharacterMock.build_characters_without_id_alien();

    //WHEN
    Integer result = useCase.execute(charactersMock);

    //THEN
    assertEquals(expected, result);
  }


}
