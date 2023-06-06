package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.GetCharacterGateway;
import cl.mobdev.rickandmorty.infraestructure.exception.GatewayException;
import cl.mobdev.rickandmorty.infraestructure.exception.NotFounException;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.mapper.ClientCharacterToCharacterMapper;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientCharacter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class GetCharacterFromRickAndMortyApiGateway implements GetCharacterGateway {

  private final RestTemplate restTemplate;
  private final ClientCharacterToCharacterMapper mapper;

  private final String urlApi;

  public GetCharacterFromRickAndMortyApiGateway(@Value("${urlApi.rickAndMorty}") String urlApi,
                                                RestTemplate restTemplate, ClientCharacterToCharacterMapper mapper) {
    this.restTemplate = restTemplate;
    this.mapper = mapper;
    this.urlApi = urlApi;
  }

  @Override
  public Character execute(int id) {
    ClientCharacter clientCharacter = getFindByIdCharactersFromApi(id);
    return mapper.mappingFromToCharacter(clientCharacter);
  }


  private ClientCharacter getFindByIdCharactersFromApi(int id) {

    ResponseEntity<ClientCharacter> response;
    ClientCharacter clientCharacter;

    try {
      response = restTemplate.getForEntity(urlApi + "/character/" + id, ClientCharacter.class);
      clientCharacter = response.getBody();

      if (null == clientCharacter) {
        throw new GatewayException("Error Character RickAndMorty API");
      }

      return clientCharacter;
    } catch (HttpClientErrorException e) {
      throw new NotFounException("Not Found Character");
    } catch (RestClientException e) {
      throw new GatewayException("Error Character RickAndMorty API");
    }
  }
}
