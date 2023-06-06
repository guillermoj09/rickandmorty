package cl.mobdev.rickandmorty.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.domain.port.output.GetAllCharactersGateway;
import cl.mobdev.rickandmorty.domain.port.output.GetAllLocationsGateway;
import cl.mobdev.rickandmorty.domain.port.output.GetLocationGateway;
import cl.mobdev.rickandmorty.domain.port.output.SaveAllCharactersByStatusRepositoryFromGateway;
import cl.mobdev.rickandmorty.mocks.CharacterMock;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveAllCharactersByStatusInRepositoryFromGatewayUseCaseTest {

  private SaveAllCharactersByStatusInRepositoryFromGatewayUseCase useCase;

  @Mock
  private SaveAllCharactersByStatusRepositoryFromGateway saveAllStatusOutput;

  @Mock
  private GetAllCharactersGateway getAllCharactersGateway;

  @Mock
  private GetLocationGateway locationGateway;

  @Mock
  private GetAllLocationsGateway getAllLocationsGateway;

  @Captor
  private ArgumentCaptor<Collection<Character>> characterCaptor;

  @BeforeEach
  void setUp() {
    useCase = new SaveAllCharactersByStatusInRepositoryFromGatewayUseCase(getAllLocationsGateway, saveAllStatusOutput,
        getAllCharactersGateway,
        locationGateway);
  }

  @Test
  void should_verify_8_characters_with_status_alive() {

    final int numberOfInvocationsExpected = 1;

    int sizeExpected = 8;


    final int resultExpected = 1;

    final String status = "Alive";

    List<String> idsLocations = Arrays.asList("1", "2", "3", "20", "5");

    //GIVEN
    Collection<Character> charactersGateway = CharacterMock.build_characters_without_locations();
    Collection<Location> locations = CharacterMock.build_locations_alive();

    Collection<Character> responseBd = CharacterMock.buildCharactersWithLocationAliveResponseBd();
    Set<String> withSomeIds = new HashSet<>(idsLocations);

    Collection<Character> charactersWithLocations = CharacterMock.buildCharactersWithLocationAlive();

    when(getAllCharactersGateway.execute()).thenReturn(charactersGateway);
    when(getAllLocationsGateway.execute(withSomeIds)).thenReturn(locations);
    //when(saveAllStatusOutput.execute(charactersWithLocations)).thenReturn(responseBd);
    //WHEN

    useCase.execute(status);

    //THEN
    verify(saveAllStatusOutput, times(numberOfInvocationsExpected)).execute(characterCaptor.capture());


    assertEquals(sizeExpected, characterCaptor.getValue().size());


  }

}