package cl.mobdev.rickandmorty.domain.port.input;

import cl.mobdev.rickandmorty.domain.model.Character;

public interface SaveCharacterInRepository {

  int save(Character character);
}
