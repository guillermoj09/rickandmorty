package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.input.SaveAllCharactersInRepository;
import cl.mobdev.rickandmorty.domain.port.output.SaveCharactersRepository;
import java.util.Collection;

public class SaveAllCharactersInRepositoryUseCase implements SaveAllCharactersInRepository {

  private final SaveCharactersRepository saveCharactersRepository;

  public SaveAllCharactersInRepositoryUseCase(
      SaveCharactersRepository saveCharactersRepository) {
    this.saveCharactersRepository = saveCharactersRepository;
  }

  @Override
  public int save(Collection<Character> characters) {
    Collection<Character> listOfCharactersSaved = this.saveCharactersRepository.save(characters);
    return listOfCharactersSaved.size();
  }
}
