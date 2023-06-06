package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.input.SaveCharacterInRepository;
import cl.mobdev.rickandmorty.domain.port.output.SaveCharacterRepository;
import java.util.Optional;

public class SaveCharacterInRepositoryUseCase implements SaveCharacterInRepository {

  private final SaveCharacterRepository saveCharacterRepository;

  public SaveCharacterInRepositoryUseCase(SaveCharacterRepository saveCharacterRepository) {
    this.saveCharacterRepository = saveCharacterRepository;
  }


  @Override
  public int save(Character character) {
    this.saveCharacterRepository.save(character);
    return 1;
  }
}
