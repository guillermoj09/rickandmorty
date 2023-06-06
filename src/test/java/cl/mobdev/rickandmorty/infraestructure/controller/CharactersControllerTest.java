package cl.mobdev.rickandmorty.infraestructure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.api.model.GetCharactersResponse;
import cl.mobdev.rickandmorty.api.model.PostCharactersRequest;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.input.GetAllCharactersSortByName;
import cl.mobdev.rickandmorty.domain.port.input.SaveAllCharactersInRepository;
import cl.mobdev.rickandmorty.infraestructure.controller.mapper.ApiCharacterCollectionsToCharacterCollectionsMapper;
import cl.mobdev.rickandmorty.infraestructure.controller.mapper.CharacterCollectionToGetCharactersResponseMapper;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CharactersControllerTest {

  private CharactersController controller;

  @Mock
  private GetAllCharactersSortByName getAllCharactersSortByName;
  @Mock
  private SaveAllCharactersInRepository saveAllCharactersInRepository;
  @Mock
  private CharacterCollectionToGetCharactersResponseMapper mapperToResponse;
  @Mock
  private ApiCharacterCollectionsToCharacterCollectionsMapper mapperToCollections;

  @BeforeEach
  void setUp() {
    this.controller =
        new CharactersController(getAllCharactersSortByName, saveAllCharactersInRepository, mapperToResponse,
            mapperToCollections);
  }

  @Test
  void should_return_200_status_when_call_use_case() {
    final int statusExpected = 200;

    //GIVEN
    Collection<Character> oneEmptyCollectionOfCharacters = new ArrayList<>();
    GetCharactersResponse oneResponseOfApi = new GetCharactersResponse();
    when(getAllCharactersSortByName.execute()).thenReturn(oneEmptyCollectionOfCharacters);
    when(mapperToResponse.mappingFrom(oneEmptyCollectionOfCharacters)).thenReturn(oneResponseOfApi);

    //WHEN - ACT
    ResponseEntity responseEntity = controller.getCharacters();

    //THEN - ASSERT
    assertEquals(statusExpected, responseEntity.getStatusCodeValue());
  }

  /*
  @Test
  void should_return_201_status_when_call_use_case() {
    final int statusExpected = 201;
    PostCharactersRequest requestMock = new PostCharactersRequest();
    requestMock.setCharacters(null);

    //GIVEN
    when(mapperToCollections.mapFrom(null)).thenReturn(null);
    when(saveAllCharactersInRepository.save(null)).thenReturn(1);

    //WHEN
    ResponseEntity responseEntity = controller.postCharacters(requestMock);

    //THEN
    assertEquals(statusExpected, responseEntity.getStatusCodeValue());
  }
  */


  @Test
  void should_call_one_time_yours_dependencies() {
    final int numberOfTimesExpected = 1;

    //GIVEN
    Collection<Character> oneEmptyCollectionOfCharacters = new ArrayList<>();
    GetCharactersResponse oneResponseOfApi = new GetCharactersResponse();
    when(getAllCharactersSortByName.execute()).thenReturn(oneEmptyCollectionOfCharacters);
    when(mapperToResponse.mappingFrom(oneEmptyCollectionOfCharacters)).thenReturn(oneResponseOfApi);

    //WHEN
    controller.getCharacters();

    //THEN
    verify(getAllCharactersSortByName, times(numberOfTimesExpected)).execute();
    verify(mapperToResponse, times(numberOfTimesExpected)).mappingFrom(oneEmptyCollectionOfCharacters);
  }

}