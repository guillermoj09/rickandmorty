package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty;

import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.domain.port.output.GetLocationGateway;
import cl.mobdev.rickandmorty.infraestructure.exception.GatewayException;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.mapper.ClientLocationToLocationMapper;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientLocation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class GetLocationFromRickAndMortyGateway implements GetLocationGateway {

  private final RestTemplate restTemplate;

  private final ClientLocationToLocationMapper mapper;

  public GetLocationFromRickAndMortyGateway(RestTemplate restTemplate, ClientLocationToLocationMapper mapper) {
    this.restTemplate = restTemplate;
    this.mapper = mapper;
  }

  @Override
  public Location execute(String url) {
    ClientLocation clientLocation = getFindByIdLocationFromApi(url);
    return this.mapper.mappingFromLocation(clientLocation);
  }

  private ClientLocation getFindByIdLocationFromApi(String apiUrlLocation) {
    ResponseEntity<ClientLocation> response;

    ClientLocation clientLocation;
    try {
      response = restTemplate.getForEntity(apiUrlLocation, ClientLocation.class);
      clientLocation = response.getBody();

      if (null == clientLocation) {
        throw new GatewayException("Error Location RickAndMorty API");
      }

    } catch (RestClientException e) {
      throw new GatewayException("Error Location RickAndMorty API");
    }
    return clientLocation;
  }
}
