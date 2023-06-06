package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.input.GetAllCharactersInRepository;
import cl.mobdev.rickandmorty.domain.port.output.GetAllCharactersRepository;
import cl.mobdev.rickandmorty.infraestructure.exception.CharacterException;

import java.util.Collection;

public class GetAllCharactersInRepositoryUseCase implements GetAllCharactersInRepository {

  private final GetAllCharactersRepository getAllCharactersRepository;

  public GetAllCharactersInRepositoryUseCase(GetAllCharactersRepository getAllCharactersRepository) {
    this.getAllCharactersRepository = getAllCharactersRepository;
  }

  @Override
  public Collection<Character> findAll() {
    Collection<Character> characters = getAllCharactersRepository.findAll();
    return characters;
  }
}
