package cl.mobdev.rickandmorty.domain.port.output;

import cl.mobdev.rickandmorty.domain.model.Character;

public interface GetCharacterGateway {
  public Character execute(int id);

}
