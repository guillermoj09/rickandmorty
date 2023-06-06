package cl.mobdev.rickandmorty.infraestructure.repository.characters;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.SaveAllCharactersByStatusRepositoryFromGateway;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa.CharacterEntityRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.CharacterCollectionsToEntityListMapper;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.EntityListToCharacterCollectionsMapper;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class SaveCharactersRepositoryFromGatewayImpl implements SaveAllCharactersByStatusRepositoryFromGateway {

  private final CharacterEntityRepository repository;

  private final CharacterCollectionsToEntityListMapper mapper;

  private final EntityListToCharacterCollectionsMapper mapperToDomain;

  public SaveCharactersRepositoryFromGatewayImpl(CharacterEntityRepository repository,
                                                 CharacterCollectionsToEntityListMapper mapper,
                                                 EntityListToCharacterCollectionsMapper mapperToDomain) {
    this.repository = repository;
    this.mapper = mapper;
    this.mapperToDomain = mapperToDomain;
  }

  @Override
  public Collection<Character> execute(Collection<Character> characters) {
    return mapperToDomain.mapFromList(repository.saveAll(mapper.mapFromList(characters)));
  }
}
