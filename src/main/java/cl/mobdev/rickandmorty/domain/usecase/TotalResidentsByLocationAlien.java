package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;
import java.util.Collection;

public class TotalResidentsByLocationAlien {

  private static final String SPECIE = "Alien";

  public Integer execute(Collection<Character> characters) {
    return characters.stream()
        .filter(x -> x.getId() != null)
        .filter(x -> SPECIE.equals(x.getSpecies()))
        .reduce(0, (subtotal, characterElement) ->
            subtotal + characterElement.getLocation().getResidents().size(), Integer::sum);
  }
}
