package cl.mobdev.rickandmorty.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;

import cl.mobdev.rickandmorty.domain.usecase.IsCharacterUseCase1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IsCharacterUseCase1Test {

  private IsCharacterUseCase1 useCase;

  @BeforeEach
  void setUp() {
    useCase = new IsCharacterUseCase1();
  }

  @Test
  void should_return_true_when_is_earth() {
    final boolean expected = true;
    //GiveN
    Character character = Character.builder()
        .withOrigin(Location.builder()
            .withName("Earth")
            .build())
        .build();
    //When
    boolean isValid = useCase.execute(character);

    //then
    assertEquals(expected, isValid);
  }

  @Test
  void should_return_false_when_other_planet() {
    final boolean expected = false;
    //GiveN
    Character character = Character.builder()
        .withOrigin(Location.builder()
            .withName("Mars")
            .build())
        .build();
    //When
    boolean isValid = useCase.execute(character);

    //then
    assertEquals(expected, isValid);
  }

  @Test
  void should_return_false_when_character_is_null() {
    final boolean expected = false;
    //GiveN
    Character character = null;
    //When
    boolean isValid = useCase.execute(character);

    //then
    assertEquals(expected, isValid);
  }




}