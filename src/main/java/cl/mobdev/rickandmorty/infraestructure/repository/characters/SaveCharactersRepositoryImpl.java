package cl.mobdev.rickandmorty.infraestructure.repository.characters;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.SaveCharactersRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa.CharacterEntityRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.CharacterCollectionsToEntityListMapper;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.EntityListToCharacterCollectionsMapper;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SaveCharactersRepositoryImpl implements SaveCharactersRepository {

  private final CharacterEntityRepository repository;
  private final CharacterCollectionsToEntityListMapper mapperToEntity;
  private final EntityListToCharacterCollectionsMapper mapperToDomain;

  public SaveCharactersRepositoryImpl(CharacterEntityRepository repository,
                                      CharacterCollectionsToEntityListMapper mapperToEntity,
                                      EntityListToCharacterCollectionsMapper mapperToDomain) {
    this.repository = repository;
    this.mapperToEntity = mapperToEntity;
    this.mapperToDomain = mapperToDomain;
  }

  @Override
  public Collection<Character> save(Collection<Character> characters) {
    List<CharacterEntity> characterEntityList = this.mapperToEntity.mapFrom(characters);
    return this.mapperToDomain.mapFrom(this.repository.saveAll(characterEntityList));
  }
}
