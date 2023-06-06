package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.SaveCharactersRepository;
import java.util.Collection;
import java.util.stream.Collectors;

public class SaveHumanCharactersInRepositoryUseCase {

  private static final String HUMAN_SPECIE = "Human";
  private final SaveCharactersRepository saveCharactersRepository;

  public SaveHumanCharactersInRepositoryUseCase(
      SaveCharactersRepository saveCharactersRepository) {
    this.saveCharactersRepository = saveCharactersRepository;
  }

  public Collection<Character> execute(Collection<Character> characters) {
    Collection<Character> humanCharacters =
        characters
            .stream()
            .filter(x -> x.getSpecies().equals(HUMAN_SPECIE))
            .collect(Collectors.toList());
    return this.saveCharactersRepository.save(humanCharacters);
  }
}
