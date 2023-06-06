package cl.mobdev.rickandmorty.infraestructure.controller;

import cl.mobdev.rickandmorty.api.CharactersApi;
import cl.mobdev.rickandmorty.api.model.GetCharactersResponse;
import cl.mobdev.rickandmorty.api.model.PostCharactersRequest;
import cl.mobdev.rickandmorty.domain.port.input.GetAllCharactersSortByName;
import cl.mobdev.rickandmorty.domain.port.input.SaveAllCharactersInRepository;
import cl.mobdev.rickandmorty.infraestructure.controller.mapper.ApiCharacterCollectionsToCharacterCollectionsMapper;
import cl.mobdev.rickandmorty.infraestructure.controller.mapper.CharacterCollectionToGetCharactersResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharactersController implements CharactersApi {

  private final GetAllCharactersSortByName getAllCharactersSortByName;
  private final CharacterCollectionToGetCharactersResponseMapper mapperToResponse;

  private final SaveAllCharactersInRepository saveAllCharactersInRepository;

  private ApiCharacterCollectionsToCharacterCollectionsMapper mapperToCollections;



  public CharactersController(GetAllCharactersSortByName getAllCharactersSortByName,
                              SaveAllCharactersInRepository saveAllCharactersInRepository,
                              CharacterCollectionToGetCharactersResponseMapper mapperToResponse,
                              ApiCharacterCollectionsToCharacterCollectionsMapper mapperToCollections) {
    this.getAllCharactersSortByName = getAllCharactersSortByName;
    this.mapperToResponse = mapperToResponse;
    this.saveAllCharactersInRepository = saveAllCharactersInRepository;
    this.mapperToCollections = mapperToCollections;
  }

  @Override
  public ResponseEntity<GetCharactersResponse> getCharacters() {
    return ResponseEntity.ok(mapperToResponse.mappingFrom(getAllCharactersSortByName.execute()));
  }

  @Override
  public ResponseEntity<Void> postCharacters(PostCharactersRequest postCharactersRequest) {
    saveAllCharactersInRepository.save(mapperToCollections.mapFrom(postCharactersRequest.getCharacters()));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
