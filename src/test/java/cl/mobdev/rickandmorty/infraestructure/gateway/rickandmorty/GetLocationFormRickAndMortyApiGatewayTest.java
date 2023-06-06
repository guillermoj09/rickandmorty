package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.infraestructure.exception.GatewayException;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.mapper.ClientLocationToLocationMapper;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientCharacter;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientLocation;
import cl.mobdev.rickandmorty.mocks.ClientLocationMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@ExtendWith(MockitoExtension.class)
class GetLocationFormRickAndMortyApiGatewayTest {

  private final String someUrl = "http://some-url";

  private final String locationEndpoint = "/location/";

  private GetLocationFromRickAndMortyGateway getLocationFromRickAndMortyGateway;

  @Mock
  private RestTemplate restTemplate;
  @Mock
  private ClientLocationToLocationMapper mapper;

  @BeforeEach
  void init() {
    getLocationFromRickAndMortyGateway = new GetLocationFromRickAndMortyGateway(restTemplate, mapper);
  }

  @Test
  void should_call_external_api_and_map_the_object() {
    final int numberOfInvocationsExpected = 1;
    //given
    String url = someUrl + locationEndpoint;

    ClientLocation clientLocation = ClientLocationMock.build_only();
    ResponseEntity responseEntityWithOk = new ResponseEntity<>(clientLocation, HttpStatus.OK);
    when(restTemplate.getForEntity(url + "1", ClientLocation.class)).thenReturn(responseEntityWithOk);

    getLocationFromRickAndMortyGateway.execute(url + "1");

    verify(restTemplate, times(numberOfInvocationsExpected)).getForEntity(url + "1", ClientLocation.class);

  }

  @Test
  void should_throw_some_error_when_api_location_response_is_null() {
    final String messageExpected = "Error Location RickAndMorty API";
    final int numberOfInvocationsExpected = 1;

    //GIVEN
    String url = someUrl + locationEndpoint;
    ClientLocation clientLocation = null;

    ResponseEntity responseEntityWithOk = new ResponseEntity<>(clientLocation, HttpStatus.OK);
    when(restTemplate.getForEntity(url + 1000, ClientLocation.class)).thenReturn(responseEntityWithOk);

    //WHEN
    GatewayException thrown = assertThrows(
        GatewayException.class,
        () -> getLocationFromRickAndMortyGateway.execute(url + 1000)
    );

    assertTrue(thrown.getMessage().contains(messageExpected));
    verify(restTemplate, times(numberOfInvocationsExpected)).getForEntity(url + 1000, ClientLocation.class);

  }


  @Test
  void should_throw_error_when_api_location_throw_some_exception() {
    final String messageExpected = "Error Location RickAndMorty API";
    final int numberOfInvocationsExpected = 1;

    //GIVEN
    String url = someUrl + locationEndpoint + "-1";
    Exception oneException = new RestClientException("some error");
    when(restTemplate.getForEntity(url, ClientLocation.class)).thenThrow(oneException);


    GatewayException thrown = assertThrows(
        GatewayException.class,
        () -> getLocationFromRickAndMortyGateway.execute(url)
    );
    System.out.println(thrown.getMessage());
    //THEN
    assertTrue(thrown.getMessage().contains(messageExpected));


  }


}
