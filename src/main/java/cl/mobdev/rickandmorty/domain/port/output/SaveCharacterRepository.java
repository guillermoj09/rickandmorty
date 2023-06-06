package cl.mobdev.rickandmorty.domain.port.output;

import cl.mobdev.rickandmorty.domain.model.Character;

public interface SaveCharacterRepository {

  Character save(Character character);


}
