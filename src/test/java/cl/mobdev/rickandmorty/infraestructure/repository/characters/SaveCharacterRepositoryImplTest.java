package cl.mobdev.rickandmorty.infraestructure.repository.characters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.infraestructure.exception.CharacterException;
import cl.mobdev.rickandmorty.infraestructure.exception.NoSaveException;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa.CharacterEntityRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.CharacterToCharacterEntityMapper;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.EntityToCharacterMapper;
import cl.mobdev.rickandmorty.mocks.CharacterEntityMock;
import cl.mobdev.rickandmorty.mocks.CharacterMock;

import java.sql.SQLException;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.hibernate.JDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveCharacterRepositoryImplTest {

  private SaveCharacterRepositoryImpl saveCharacterRepository;

  @Mock
  private CharacterToCharacterEntityMapper mapperToEntity;

  @Mock
  private EntityToCharacterMapper mapperToDomain;

  @Mock
  private CharacterEntityRepository repository;

  @BeforeEach
  void setUp() {
    saveCharacterRepository = new SaveCharacterRepositoryImpl(repository, mapperToEntity, mapperToDomain);
  }

  @Test
  void should_use_repository_and_return_mapping_result() {

    final int numberOfInvocationsExpected = 1;
    //GIVEN
    CharacterEntity oneCharacterEntity = CharacterEntityMock.build_only();
    Character charactersInput = CharacterMock.build_character_api();
    when(mapperToEntity.mapFrom(charactersInput)).thenReturn(oneCharacterEntity);
    when(repository.save(oneCharacterEntity)).thenReturn(oneCharacterEntity);
    //WHEN
    saveCharacterRepository.save(charactersInput);
    //THEN
    verify(mapperToEntity, times(numberOfInvocationsExpected)).mapFrom(charactersInput);
    verify(repository, times(numberOfInvocationsExpected)).save(oneCharacterEntity);
  }

  @Test
  void should_return_null_when_name_exists() {
    final String nameSome = "Rick Sanchez";
    final String messageExpected = "Exist Character";
    //GIVEN
    CharacterEntity oneCharacterEntity = CharacterEntityMock.build_only();
    Character charactersInput = CharacterMock.build_character_api();
    when(repository.findByName(nameSome)).thenReturn(Optional.of(oneCharacterEntity));
    //WHEN
    CharacterException thrown = assertThrows(
        CharacterException.class,
        () -> saveCharacterRepository.save(charactersInput)

    );
    //THEN
    assertTrue(thrown.getMessage().contains(messageExpected));

  }

  @Test
  void should_return_null_when_save_throw_exception() {
    final int numberOfInvocationsExpected = 1;
    final String messageExpected = "Not Save Character";
    final String nameSome = "some";

    CharacterEntity oneCharacterEntity = CharacterEntityMock.build_only();
    CharacterEntity characterEntity = CharacterEntityMock.build_only();

    Character charactersInput = CharacterMock.build_character_api();

    //GIVEN
    JDBCException ex1 = new JDBCConnectionException("sql", new SQLException("reason"));

    when(mapperToEntity.mapFrom(charactersInput)).thenThrow(ex1);
    //WHEN
    NoSaveException thrown = assertThrows(
        NoSaveException.class,
        () -> saveCharacterRepository.save(charactersInput)

    );
    assertTrue(thrown.getMessage().contains(messageExpected));
  }

}