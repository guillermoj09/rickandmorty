package cl.mobdev.rickandmorty.domain.port.input;

import cl.mobdev.rickandmorty.domain.model.Character;
import java.util.Collection;

public interface SaveAllCharactersInRepository {

  int save(Collection<Character> characters);

}
