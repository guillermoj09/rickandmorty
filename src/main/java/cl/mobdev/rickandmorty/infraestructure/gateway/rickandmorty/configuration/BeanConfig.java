package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.configuration;

import cl.mobdev.rickandmorty.domain.port.input.DeleteCharacterInRepository;
import cl.mobdev.rickandmorty.domain.port.input.GetAllCharactersInRepository;
import cl.mobdev.rickandmorty.domain.port.input.GetAllCharactersSortByName;
import cl.mobdev.rickandmorty.domain.port.input.GetCharacterById;
import cl.mobdev.rickandmorty.domain.port.input.SaveAllCharactersByStatusInRepositoryFromGateway;
import cl.mobdev.rickandmorty.domain.port.input.SaveAllCharactersInRepository;
import cl.mobdev.rickandmorty.domain.port.input.SaveCharacterInRepository;
import cl.mobdev.rickandmorty.domain.port.output.DeleteCharacterRepository;
import cl.mobdev.rickandmorty.domain.port.output.GetAllCharactersRepository;
import cl.mobdev.rickandmorty.domain.port.output.SaveAllCharactersByStatusRepositoryFromGateway;
import cl.mobdev.rickandmorty.domain.port.output.SaveCharacterRepository;
import cl.mobdev.rickandmorty.domain.port.output.SaveCharactersRepository;
import cl.mobdev.rickandmorty.domain.usecase.DeleteCharacterInRepositoryUseCase;
import cl.mobdev.rickandmorty.domain.usecase.GetAllCharactersInRepositoryUseCase;
import cl.mobdev.rickandmorty.domain.usecase.GetAllCharactersSortByNameUseCase;
import cl.mobdev.rickandmorty.domain.usecase.GetCharacterByIdUseCase;
import cl.mobdev.rickandmorty.domain.usecase.SaveAllCharactersByStatusInRepositoryFromGatewayUseCase;
import cl.mobdev.rickandmorty.domain.usecase.SaveAllCharactersInRepositoryUseCase;
import cl.mobdev.rickandmorty.domain.usecase.SaveCharacterInRepositoryUseCase;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.GetAllCharactersFromRickAndMortyApiGateway;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.GetAllLocationsFromRickAndMortyGateway;

import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.GetCharacterFromRickAndMortyApiGateway;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.GetLocationFromRickAndMortyGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public GetAllCharactersSortByName getAllCharactersSortByName(
      GetAllCharactersFromRickAndMortyApiGateway characterGateway,
      GetAllLocationsFromRickAndMortyGateway locationGateway) {
    return new GetAllCharactersSortByNameUseCase(characterGateway, locationGateway);
  }

  @Bean
  public GetCharacterById getCharacterById(
      GetCharacterFromRickAndMortyApiGateway characterGateway,
      GetLocationFromRickAndMortyGateway locationGateway) {
    return new GetCharacterByIdUseCase(characterGateway, locationGateway);
  }

  @Bean
  public SaveAllCharactersInRepository saveAllCharactersInRepository(
      SaveCharactersRepository saveCharactersRepository
  ) {
    return new SaveAllCharactersInRepositoryUseCase(saveCharactersRepository);
  }

  @Bean
  public SaveCharacterInRepository saveCharacterInRepository(SaveCharacterRepository saveCharacterRepository) {
    return new SaveCharacterInRepositoryUseCase(saveCharacterRepository);
  }

  @Bean
  public DeleteCharacterInRepository deleteCharacterInRepository(DeleteCharacterRepository deleteCharacterRepository) {
    return new DeleteCharacterInRepositoryUseCase(deleteCharacterRepository);
  }

  @Bean
  public GetAllCharactersInRepository getAllCharactersInRepository(
      GetAllCharactersRepository getAllCharactersRepository) {
    return new GetAllCharactersInRepositoryUseCase(getAllCharactersRepository);
  }

  @Bean
  public SaveAllCharactersByStatusInRepositoryFromGateway saveAllCharactersByStatusInRepositoryFromGateway(
      GetAllLocationsFromRickAndMortyGateway locationsGateway,SaveAllCharactersByStatusRepositoryFromGateway saveAllCharactersByStatusRepositoryFromGateway,
      GetAllCharactersFromRickAndMortyApiGateway characterGateway,
      GetLocationFromRickAndMortyGateway locationGateway) {
    return new SaveAllCharactersByStatusInRepositoryFromGatewayUseCase(locationsGateway,
        saveAllCharactersByStatusRepositoryFromGateway,
        characterGateway, locationGateway);
  }


}
