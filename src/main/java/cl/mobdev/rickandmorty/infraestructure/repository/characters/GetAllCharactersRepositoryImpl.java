package cl.mobdev.rickandmorty.infraestructure.repository.characters;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.GetAllCharactersRepository;
import cl.mobdev.rickandmorty.infraestructure.exception.CharacterException;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa.CharacterEntityRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.EntityListToCharacterCollectionsMapper;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GetAllCharactersRepositoryImpl implements GetAllCharactersRepository {

  private final CharacterEntityRepository repository;

  private final EntityListToCharacterCollectionsMapper mapper;


  public GetAllCharactersRepositoryImpl(CharacterEntityRepository repository,
                                        EntityListToCharacterCollectionsMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Collection<Character> findAll() {

    List<CharacterEntity> characterEntities = repository.findAll();

    if (characterEntities.size() < 1) {
      throw new CharacterException("there are no Characters");
    }
    Collection<Character> characters = mapper.mapFromList(characterEntities);
    return characters;
  }
}
