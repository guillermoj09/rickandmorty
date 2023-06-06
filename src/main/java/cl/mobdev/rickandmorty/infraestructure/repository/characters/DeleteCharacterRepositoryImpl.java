package cl.mobdev.rickandmorty.infraestructure.repository.characters;

import cl.mobdev.rickandmorty.domain.port.output.DeleteCharacterRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.StringToUuidMapper;
import cl.mobdev.rickandmorty.infraestructure.exception.CharacterException;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa.CharacterEntityRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.locations.jpa.LocationEntityRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.origin.jpa.OriginEntityRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DeleteCharacterRepositoryImpl implements DeleteCharacterRepository {

  private final CharacterEntityRepository repository;

  private final LocationEntityRepository locationEntityRepository;

  private final OriginEntityRepository originEntityRepository;

  private final StringToUuidMapper mapper;

  public DeleteCharacterRepositoryImpl(CharacterEntityRepository repository,
                                       LocationEntityRepository locationEntityRepository,
                                       OriginEntityRepository originEntityRepository, StringToUuidMapper mapper) {
    this.repository = repository;
    this.locationEntityRepository = locationEntityRepository;
    this.originEntityRepository = originEntityRepository;
    this.mapper = mapper;
  }

  @Override
  public void delete(String id) {
    System.out.println("1");
    Optional<CharacterEntity> characterEntityOptional = this.repository.findById(mapper.mapFrom(id));
    System.out.println("2");
    if (!characterEntityOptional.isPresent()) {
      throw new CharacterException("No Exist Character");
    }
    System.out.println("3");
    repository.deleteById(mapper.mapFrom(id));
    System.out.println("4");
  }
}
