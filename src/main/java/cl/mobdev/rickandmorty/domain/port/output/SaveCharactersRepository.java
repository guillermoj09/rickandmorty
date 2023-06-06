package cl.mobdev.rickandmorty.domain.port.output;

import cl.mobdev.rickandmorty.domain.model.Character;
import java.util.Collection;

public interface SaveCharactersRepository {

  Collection<Character> save(Collection<Character> characters);

}
