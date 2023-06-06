package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.port.input.DeleteCharacterInRepository;
import cl.mobdev.rickandmorty.domain.port.output.DeleteCharacterRepository;

public class DeleteCharacterInRepositoryUseCase implements DeleteCharacterInRepository {

  private final DeleteCharacterRepository deleteCharacterRepository;

  public DeleteCharacterInRepositoryUseCase(DeleteCharacterRepository deleteCharacterRepository) {
    this.deleteCharacterRepository = deleteCharacterRepository;
  }


  @Override
  public int execute(String id) {
    deleteCharacterRepository.delete(id);
    return 1;
  }
}
