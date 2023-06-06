package cl.mobdev.rickandmorty.infraestructure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.api.model.ApiCharacter;
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
class CharacterControllerTest {

  private CharacterController controller;

  @Mock
  private GetCharacterById getCharacterById;

  @Mock
  private SaveCharacterInRepository saveCharacterInRepository;
  @Mock
  private CharacterToGetCharacterResponseMapper mapperToResponse;

  @Mock
  private ApiCharacterToCharacterMapper mapperApiCharacterToCharacter;

  @Mock
  private DeleteCharacterInRepository deleteCharacterInRepository;

  @Mock
  private GetAllCharactersInRepository getAllCharactersInRepository;

  @Mock
  private SaveAllCharactersByStatusInRepositoryFromGateway saveAllCharactersByStatusInRepositoryFromGateway;

  private CharacterCollectionToApiCharacterDbMapper characterCollectionToApiCharacterDbMapper;

  @BeforeEach
  void setUp() {
    this.controller = new CharacterController(getCharacterById, mapperToResponse, getAllCharactersInRepository,
        mapperApiCharacterToCharacter,
        saveCharacterInRepository, deleteCharacterInRepository, characterCollectionToApiCharacterDbMapper,
        saveAllCharactersByStatusInRepositoryFromGateway);
  }


  @Test
  void should_return_200_status_when_call_use_case() {
    final int statusExpected = 200;
    Character character = new Character();
    //GIVEN
    GetCharacterResponse oneResponseOfApi = new GetCharacterResponse();
    when(getCharacterById.execute(1)).thenReturn(character);
    when(mapperToResponse.mappingFrom(character)).thenReturn(oneResponseOfApi);

    //WHEN
    ResponseEntity responseEntity = controller.getCharacter(1);

    //THEN
    assertEquals(statusExpected, responseEntity.getStatusCodeValue());
  }


  @Test
  void should_return_201_status_when_call_use_case() {
    final int statusExpected = 201;
    ApiCharacter requestMock = null;

    //GIVEN
    when(mapperApiCharacterToCharacter.mapFrom(null)).thenReturn(null);
    when(saveCharacterInRepository.save(null)).thenReturn(1);

    //WHEN
    ResponseEntity responseEntity = controller.characterPost(requestMock);
    System.out.println(responseEntity.getStatusCodeValue());
    //THEN
    assertEquals(statusExpected, responseEntity.getStatusCodeValue());
  }
}
