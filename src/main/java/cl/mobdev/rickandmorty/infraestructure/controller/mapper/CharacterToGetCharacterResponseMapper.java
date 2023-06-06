package cl.mobdev.rickandmorty.infraestructure.controller.mapper;

import cl.mobdev.rickandmorty.api.model.ApiOrigin;
import cl.mobdev.rickandmorty.api.model.GetCharacterResponse;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;

import org.springframework.stereotype.Component;


@Component
public class CharacterToGetCharacterResponseMapper {


  public GetCharacterResponse mappingFrom(Character character) {

    Integer episodeCount = character.getEpisode().size();
    GetCharacterResponse getCharacterResponse = new GetCharacterResponse();
    getCharacterResponse.setId(character.getId());
    getCharacterResponse.setName(character.getName());
    getCharacterResponse.setStatus(character.getStatus());
    getCharacterResponse.setSpecies(character.getSpecies());
    getCharacterResponse.setType(character.getType());
    getCharacterResponse.setOrigin(locationToMapperOriginResponse(character.getLocation()));
    getCharacterResponse.setEpisodeCount(episodeCount);

    return getCharacterResponse;
  }

  private ApiOrigin locationToMapperOriginResponse(Location location) {
    ApiOrigin apiOrigin = new ApiOrigin();
    if (location != null) {
      apiOrigin.setName(location.getName());
      apiOrigin.setDimension(location.getDimension());
      apiOrigin.setResidents(location.getResidents());
      apiOrigin.setUrl(location.getUrl());
    }
    return apiOrigin;
  }
}
