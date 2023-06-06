package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.GetCharacterGateway;

public class GetCharacterIsValidHumanUseCase2 {

  private final GetCharacterGateway getCharacterGateway;

  public GetCharacterIsValidHumanUseCase2(GetCharacterGateway getCharacterGateway) {
    this.getCharacterGateway = getCharacterGateway;
  }

  public boolean execute(int id) {
    final String specie = "Human";
    Character character = getCharacterGateway.execute(id);
    return specie.equals(character.getSpecies());
  }
}
