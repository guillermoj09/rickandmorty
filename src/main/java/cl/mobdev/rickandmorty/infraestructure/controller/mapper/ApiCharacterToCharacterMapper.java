package cl.mobdev.rickandmorty.infraestructure.controller.mapper;

import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import cl.mobdev.rickandmorty.api.model.ApiLocation;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import org.springframework.stereotype.Component;

@Component
public class ApiCharacterToCharacterMapper {

  public Character mapFrom(ApiCharacter apiCharacter) {

    return Character.builder()
        .withName(apiCharacter.getName())
        .withType(apiCharacter.getType())
        .withSpecies(apiCharacter.getSpecies())
        .withStatus(apiCharacter.getStatus())
        .withLocation(apiLocationToLocation(apiCharacter.getLocation()))
        .withOrigin(apiOriginToOriginApiCharacter(apiCharacter))
        .build();

  }

  //Recibir ApiLocation
  private Location apiLocationToLocation(ApiLocation apiLocation) {
    return apiLocation == null ? null : Location.builder()
        .withName(apiLocation.getName())
        .withDimension(apiLocation.getDimension())
        .withType(apiLocation.getType())
        .build();

  }

  private Location apiOriginToOriginApiCharacter(ApiCharacter apiCharacter) {
    return apiCharacter.getOrigin() == null ? null : Location.builder()
        .withName(apiCharacter.getOrigin().getName())
        .withDimension(apiCharacter.getOrigin().getDimension())
        .withType(apiCharacter.getOrigin().getType())
        .build();
  }

}
