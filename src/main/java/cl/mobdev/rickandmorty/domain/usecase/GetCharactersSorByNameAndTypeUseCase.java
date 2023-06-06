package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.GetAllCharactersGateway;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class GetCharactersSorByNameAndTypeUseCase {

  private static final String PLANET_MARS = "Mars";

  private static final String EMPTY = "";
  private final GetAllCharactersGateway getAllCharactersGateway;

  private final Comparator<Character> comparator = Comparator.comparing(Character::getName);


  public GetCharactersSorByNameAndTypeUseCase(GetAllCharactersGateway getAllCharactersGateway) {
    this.getAllCharactersGateway = getAllCharactersGateway;
  }

  public Collection<Character> execute() {
    Collection<Character> charactersGateway = getAllCharactersGateway.execute();
    Collection<Character> characters = writeAgainAttributeType(charactersGateway);
    return characters.stream().sorted(comparator).collect(Collectors.toList());
  }

  private Collection<Character> writeAgainAttributeType(Collection<Character> characters) {
    characters.stream()
        .filter(x -> PLANET_MARS.equals(x.getLocation().getName()))
        .forEach(x -> x.setType(isMars(x.getName())));
    return characters;

  }

  private String isMars(String arg) {
    return arg.replace("a", "1")
        .replace("e", "2")
        .replace("i", "3")
        .replace("o", "4")
        .replace("u", "5");
  }

}
