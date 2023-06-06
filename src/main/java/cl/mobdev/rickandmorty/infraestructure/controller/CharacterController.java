package cl.mobdev.rickandmorty.infraestructure.controller;

import cl.mobdev.rickandmorty.api.CharacterApi;
import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import cl.mobdev.rickandmorty.api.model.ApiCharacterDb;
import cl.mobdev.rickandmorty.api.model.GetCharacterResponse;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.input.DeleteCharacterInRepository;
import cl.mobdev.rickandmorty.domain.port.input.GetAllCharactersInRepository;
import cl.mobdev.rickandmorty.domain.port.input.GetCharacterById;
import cl.mobdev.rickandmorty.domain.port.input.SaveAllCharactersByStatusInRepositoryFromGateway;
import cl.mobdev.rickandmorty.domain.port.input.SaveCharacterInRepository;
import cl.mobdev.rickandmorty.infraestructure.controller.mapper.ApiCharacterToCharacterMapper;
import cl.mobdev.rickandmorty.infraestructure.controller.mapper.CharacterCollectionToApiCharacterDbMapper;
import cl.mobdev.rickandmorty.infraestructure.controller.mapper.CharacterToGetCharacterResponseMapper;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController implements CharacterApi {

  private final GetCharacterById getCharacterById;
  private final CharacterToGetCharacterResponseMapper mapperToResponse;

  private final GetAllCharactersInRepository getAllCharactersInRepository;

  private final ApiCharacterToCharacterMapper mapperApiCharacterToCharacter;

  private final SaveCharacterInRepository saveCharacterInRepository;

  private final DeleteCharacterInRepository deleteCharacterInRepository;

  private final CharacterCollectionToApiCharacterDbMapper characterCollectionToApiCharacterDbMapper;

  private final SaveAllCharactersByStatusInRepositoryFromGateway saveAllCharactersByStatusInRepositoryFromGateway;

  public CharacterController(GetCharacterById getCharacterById,
                             CharacterToGetCharacterResponseMapper mapperToResponse,
                             GetAllCharactersInRepository getAllCharactersInRepository, ApiCharacterToCharacterMapper mapperApiCharacterToCharacter,
                             SaveCharacterInRepository saveCharacterInRepository,
                             DeleteCharacterInRepository deleteCharacterInRepository,
                             CharacterCollectionToApiCharacterDbMapper characterCollectionToApiCharacterDbMapper,
                             SaveAllCharactersByStatusInRepositoryFromGateway saveAllCharactersByStatusInRepositoryFromGateway) {
    this.getCharacterById = getCharacterById;
    this.mapperToResponse = mapperToResponse;
    this.getAllCharactersInRepository = getAllCharactersInRepository;
    this.mapperApiCharacterToCharacter = mapperApiCharacterToCharacter;
    this.saveCharacterInRepository = saveCharacterInRepository;
    this.deleteCharacterInRepository = deleteCharacterInRepository;
    this.characterCollectionToApiCharacterDbMapper = characterCollectionToApiCharacterDbMapper;
    this.saveAllCharactersByStatusInRepositoryFromGateway = saveAllCharactersByStatusInRepositoryFromGateway;
  }



  @Override
  public ResponseEntity<Void> characterPost(ApiCharacter character) {
    saveCharacterInRepository.save(mapperApiCharacterToCharacter.mapFrom(character));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> deleteCharacter(String id) {
    deleteCharacterInRepository.execute(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<ApiCharacterDb>> getAllCharacter() {
    Collection<Character> characters = getAllCharactersInRepository.findAll();
    return ResponseEntity.ok(characterCollectionToApiCharacterDbMapper.mappingFrom(characters));
  }
  @Override
  public ResponseEntity<GetCharacterResponse> getCharacter(Integer id) {
    Character character = getCharacterById.execute(id);
    return ResponseEntity.ok(mapperToResponse.mappingFrom(character));
  }
  @Override
  public ResponseEntity<Void> saveAllCharacterInRepositoryFromGateway(String status) {
    saveAllCharactersByStatusInRepositoryFromGateway.execute(status);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
