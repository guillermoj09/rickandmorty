package cl.mobdev.rickandmorty.domain.port.input;

import java.util.Collection;
import cl.mobdev.rickandmorty.domain.model.Character;


public interface GetAllCharactersInRepository {

  Collection<Character> findAll();

}
