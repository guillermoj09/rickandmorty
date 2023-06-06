package cl.mobdev.rickandmorty.infraestructure.repository.characters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.infraestructure.exception.CharacterException;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa.CharacterEntityRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.EntityListToCharacterCollectionsMapper;
import cl.mobdev.rickandmorty.mocks.CharacterEntityMock;
import cl.mobdev.rickandmorty.mocks.CharacterMock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllCharactersRepositoryImplTest {

  private GetAllCharactersRepositoryImpl getAllCharactersRepositoryImpl;

  @Mock
  private CharacterEntityRepository repository;

  @Mock
  private EntityListToCharacterCollectionsMapper mapper;

  @BeforeEach
  void setUp() {
    getAllCharactersRepositoryImpl = new GetAllCharactersRepositoryImpl(repository, mapper);
  }

  @Test
  void should_verify_findAll_success() {
    int numberOfInvocationsExpected = 1;
    //Given
    List<Character> characterList = CharacterMock.listCharactersWithOriginAndLocationNullImpl();
    List<CharacterEntity> characterEntitiesList = CharacterEntityMock.listCharactersWithOriginAndLocationNullImpl();

    when(repository.findAll()).thenReturn(characterEntitiesList);
    when(mapper.mapFromList(characterEntitiesList)).thenReturn(characterList);

    //When
    getAllCharactersRepositoryImpl.findAll();

    verify(repository, times(numberOfInvocationsExpected)).findAll();

  }


  @Test
  void should_return_exception_when_listEntities_is_less_than_1() {
    final String messageExpected = "there are no Characters";
    //Given
    List<Character> characterList = CharacterMock.listCharactersWithOriginAndLocationNullImpl();
    List<CharacterEntity> characterEntitiesList = new ArrayList<>();

    when(repository.findAll()).thenReturn(characterEntitiesList);


    //When
    CharacterException thrown = assertThrows(
        CharacterException.class,
        () -> getAllCharactersRepositoryImpl.findAll()
    );

    System.out.println(thrown.getMessage());

    assertTrue(thrown.getMessage().contains(messageExpected));

  }


}