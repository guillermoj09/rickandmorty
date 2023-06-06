package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.infraestructure.exception.GatewayException;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.mapper.ClientCharacterToCharacterMapper;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientCharacter;
import cl.mobdev.rickandmorty.mocks.ClientCharacterMock;

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
class GetCharacterFromRickAndMortyApiGatewayTest {

  private final String someUrl = "http://some-url";

  private final String characterEndpoint = "/character/";


  private GetCharacterFromRickAndMortyApiGateway gateway;

  @Mock
  private ClientCharacterToCharacterMapper mapper;
  @Mock
  private RestTemplate restTemplate;

  @BeforeEach
  void init() {

    gateway = new GetCharacterFromRickAndMortyApiGateway(someUrl, restTemplate, mapper);

  }

  @Test
  void should_call_external_api_and_map_the_object() {
    final int numberOfInvocationsExpected = 1;
    //given
    String url = someUrl + characterEndpoint;
    ClientCharacter clientCharacter = ClientCharacterMock.build_only();
    ResponseEntity responseEntityWithOk = new ResponseEntity<>(clientCharacter, HttpStatus.OK);
    when(restTemplate.getForEntity(url + "1", ClientCharacter.class)).thenReturn(responseEntityWithOk);

    //When
    gateway.execute(1);

    //THEN
    verify(restTemplate, times(numberOfInvocationsExpected)).getForEntity(url + "1", ClientCharacter.class);
    verify(mapper, times(numberOfInvocationsExpected)).mappingFromToCharacter(clientCharacter);


  }

  @Test
  void should_throw_error_when_api_character_throw_some_exception() {
    final String messageExpected = "Error Character RickAndMorty API";
    final int numberOfInvocationsExpected = 1;

    //GIVEN
    String url = someUrl + characterEndpoint + "-1";
    Exception oneException = new RestClientException("some error");
    when(restTemplate.getForEntity(url, ClientCharacter.class)).thenThrow(oneException);


    GatewayException thrown = assertThrows(
        GatewayException.class,
        () -> gateway.execute(-1)
    );

    //THEN
    assertTrue(thrown.getMessage().contains(messageExpected));


  }


  @Test
  void should_throw_some_error_when_api_character_response_is_null() {

    final String messageExpected = "Error Character RickAndMorty API";
    final int numberOfInvocationsExpected = 1;

    //GIVEN
    String url = someUrl.concat(characterEndpoint);
    ClientCharacter clientCharacter = null;
    ResponseEntity responseEntityWithOk = new ResponseEntity<>(clientCharacter, HttpStatus.OK);
    when(restTemplate.getForEntity(url + 1000, ClientCharacter.class)).thenReturn(responseEntityWithOk);

    //WHEN
    GatewayException thrown = assertThrows(
        GatewayException.class,
        () -> gateway.execute(1000)
    );

    //THEN
    assertTrue(thrown.getMessage().contains(messageExpected));
    verify(restTemplate, times(numberOfInvocationsExpected)).getForEntity(url + 1000, ClientCharacter.class);
  }


}

