package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.domain.port.input.SaveAllCharactersByStatusInRepositoryFromGateway;
import cl.mobdev.rickandmorty.domain.port.output.GetAllCharactersGateway;
import cl.mobdev.rickandmorty.domain.port.output.GetAllLocationsGateway;
import cl.mobdev.rickandmorty.domain.port.output.GetLocationGateway;
import cl.mobdev.rickandmorty.domain.port.output.SaveAllCharactersByStatusRepositoryFromGateway;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SaveAllCharactersByStatusInRepositoryFromGatewayUseCase
    implements SaveAllCharactersByStatusInRepositoryFromGateway {

  private static final String URL_LOCATION = "https://rickandmortyapi.com/api/location/";

  private static Location locationUnknown = Location.builder()
      .withName("unknown")
      .withDimension("")
      .withType("")
      .build();

  private static final String EMPTY = "";

  private final GetAllLocationsGateway getAllLocationsGateway;


  private final SaveAllCharactersByStatusRepositoryFromGateway saveAllStatusOutput;

  private final GetAllCharactersGateway gateway;

  private final GetLocationGateway locationGateway;

  public SaveAllCharactersByStatusInRepositoryFromGatewayUseCase(
      GetAllLocationsGateway getAllLocationsGateway, SaveAllCharactersByStatusRepositoryFromGateway saveAllStatusOutput,
      GetAllCharactersGateway getAllCharactersGateway, GetLocationGateway locationGateway) {
    this.getAllLocationsGateway = getAllLocationsGateway;
    this.saveAllStatusOutput = saveAllStatusOutput;
    this.gateway = getAllCharactersGateway;
    this.locationGateway = locationGateway;
  }


  @Override
  public int execute(String status) {

    System.out.println(status);
    Collection<Character> charactersGateway = gateway.execute();
    //Collection<Character> charactersList = new ArrayList<>();

    Collection<Character> characterCollection = charactersGateway.stream()
        .filter(c -> status.equals(c.getStatus())
        ).collect(Collectors.toList());

    Set<String> locationsIds = extractLocationIdsFrom(characterCollection);
    Collection<Location> locations = getAllLocationsGateway.execute(locationsIds);
    Collection<Character> characters = findLocations(characterCollection, locations);


    saveAllStatusOutput.execute(characters);

    return 1;
  }

  private Set<String> extractLocationIdsFrom(Collection<Character> characters) {
    Set<String> locationIds = characters
        .stream()
        .filter(x -> x.getLocation().getId() != null)
        .map(x -> x.getLocation().getId())
        .map(Object::toString)
        .collect(Collectors.toSet());

    Set<String> originIds = characters
        .stream()
        .filter(x -> x.getOrigin().getId() != null)
        .map(x -> x.getOrigin().getId())
        .map(Object::toString)
        .collect(Collectors.toSet());

    locationIds.addAll(originIds);

    return locationIds;
  }

  private Collection<Character> findLocations(Collection<Character> charactersWithoutLocation,
                                              Collection<Location> locations) {

    Collection<Character> characters = new ArrayList<>();

    for (Character character : charactersWithoutLocation) {
      character.setOrigin(locations
          .stream()
          .filter(x -> x.getId().equals(character.getOrigin().getId()))
          .findFirst()
          .orElse(locationUnknown));
      character.setLocation(locations
          .stream()
          .filter(x -> x.getId().equals(character.getLocation().getId()))
          .findFirst()
          .orElse(locationUnknown));
      characters.add(character);
    }

    return characters;
  }

}
