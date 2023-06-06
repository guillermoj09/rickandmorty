package cl.mobdev.rickandmorty.infraestructure.repository.characters;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.SaveCharacterRepository;
import cl.mobdev.rickandmorty.infraestructure.exception.CharacterException;
import cl.mobdev.rickandmorty.infraestructure.exception.NoSaveException;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa.CharacterEntityRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.CharacterToCharacterEntityMapper;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.EntityToCharacterMapper;
import java.util.Optional;
import org.hibernate.JDBCException;
import org.springframework.stereotype.Service;

@Service
public class SaveCharacterRepositoryImpl implements SaveCharacterRepository {

  private final CharacterEntityRepository repository;

  private final CharacterToCharacterEntityMapper mapperToEntity;

  private final EntityToCharacterMapper mapperToDomain;

  public SaveCharacterRepositoryImpl(CharacterEntityRepository repository,
                                     CharacterToCharacterEntityMapper mapperToEntity,
                                     EntityToCharacterMapper mapperToDomain) {
    this.repository = repository;
    this.mapperToEntity = mapperToEntity;
    this.mapperToDomain = mapperToDomain;
  }


  @Override
  public Character save(Character character) {
    Optional<CharacterEntity> name = this.repository.findByName(character.getName());
    System.out.println("entro 1");
    if (name.isPresent()) {
      throw new CharacterException("Exist Character");
    }
    try {
      System.out.println("entro");
      CharacterEntity characterEntity = this.mapperToEntity.mapFrom(character);
      return this.mapperToDomain.mapFrom(this.repository.save(characterEntity));
    } catch (JDBCException e) {
      System.out.println(e.getMessage());
      throw new NoSaveException("Not Save Character");
    }
  }

}
