package cl.mobdev.rickandmorty.infraestructure.repository.characters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.StringToUuidMapper;
import cl.mobdev.rickandmorty.infraestructure.exception.CharacterException;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa.CharacterEntityRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.locations.jpa.LocationEntityRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.origin.jpa.OriginEntityRepository;
import cl.mobdev.rickandmorty.mocks.CharacterEntityMock;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteCharacterRepositoryImplTest {

  @Mock
  private CharacterEntityRepository repository;

  private DeleteCharacterRepositoryImpl deleteCharacterRepository;
  @Mock
  private LocationEntityRepository locationEntityRepository;
  @Mock
  private OriginEntityRepository originEntityRepository;

  @Mock
  private StringToUuidMapper mapper;

  @BeforeEach
  void setUp() {
    deleteCharacterRepository = new DeleteCharacterRepositoryImpl(repository, locationEntityRepository,
        originEntityRepository, mapper);
  }

  @Test
  void should_return_exception_no_exist() {

    UUID uuidStatic = UUID.fromString("e76fb43a-0e83-46b2-8753-7903ef3ef168");
    final String id = "1221";
    final String messageExpected = "No Exist Character";

    //GIVEN
    Collection<CharacterEntity> listEntities = CharacterEntityMock.build();

    when(mapper.mapFrom(id)).thenReturn(uuidStatic);
    when(repository.findById(uuidStatic)).thenReturn(Optional.empty());

    //WHEN
    CharacterException thrown = assertThrows(
        CharacterException.class,
        () -> deleteCharacterRepository.delete(id)
    );
    //THEN
    assertTrue(thrown.getMessage().contains(messageExpected));
  }


  @Test
  void should_execute_delete_repository() {
    final int numberOfInvocationsExpected = 1;
    UUID uuidStatic = UUID.fromString("e76fb43a-0e83-46b2-8753-7903ef3ef168");
    final String id = "1221";
    //GIVEN
    CharacterEntity characterEntityMock = CharacterEntityMock.build_only();

    when(mapper.mapFrom(id)).thenReturn(uuidStatic);
    when(repository.findById(uuidStatic)).thenReturn(Optional.of(characterEntityMock));
    //WHEN
    deleteCharacterRepository.delete(id);
    //THEN
    verify(repository, times(numberOfInvocationsExpected)).deleteById(uuidStatic);

  }

}