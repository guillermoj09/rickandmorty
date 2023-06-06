package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.infraestructure.exception.LocationException;

public class GetCharacterUseCase4 {

  private static final String LOCATION_UNKNOWN = "unknown";

  public boolean execute(Character character) {


    if (LOCATION_UNKNOWN.equals(character.getOrigin().getName())
        &&
        LOCATION_UNKNOWN.equals(character.getLocation().getName())) {
      throw new LocationException("Origen y locacion desconocidos");

    } else if (character.getLocation().getName().equals(character.getOrigin().getName())) {
      return true;

    } else if (LOCATION_UNKNOWN.equals(character.getOrigin().getName())) {
      throw new LocationException("Origen desconocido");
    } else if (LOCATION_UNKNOWN.equals(character.getLocation().getName())) {
      throw new LocationException("Locacion desconocido");
    }
    return false;
  }
}
