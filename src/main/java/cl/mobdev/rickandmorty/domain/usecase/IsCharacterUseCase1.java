package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;

public class IsCharacterUseCase1 {
  public boolean execute(Character character) {

    String planetEarth = "Earth";

    return character != null && planetEarth.equals(character.getOrigin().getName());
  }
}
