package cl.mobdev.rickandmorty.infraestructure.repository.characters;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa.CharacterEntityRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.CharacterCollectionsToEntityListMapper;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.EntityListToCharacterCollectionsMapper;
import cl.mobdev.rickandmorty.mocks.CharacterEntityMock;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveCharactersRepositoryImplTest {

  private SaveCharactersRepositoryImpl saveCharactersRepository;

  @Mock
  private CharacterEntityRepository repository;
  @Mock
  private CharacterCollectionsToEntityListMapper mapperToEntity;
  @Mock
  private EntityListToCharacterCollectionsMapper mapperToDomain;

  @BeforeEach
  void setUp() {
    saveCharactersRepository = new SaveCharactersRepositoryImpl(repository, mapperToEntity, mapperToDomain);
  }

  @Test
  void should_use_repository_and_return_mapping_results() {
    final int numberOfInvocationsExpected = 1;

    //GIVEN
    List<CharacterEntity> oneListOfCharacterEntities = CharacterEntityMock.build();
    Collection<Character> charactersInput = CharacterMock.build_characters_with_origins_and_locations();
    Collection<Character> charactersOutput = CharacterMock.build_from_repository();
    when(mapperToEntity.mapFrom(charactersInput)).thenReturn(oneListOfCharacterEntities);
    when(repository.saveAll(oneListOfCharacterEntities)).thenReturn(oneListOfCharacterEntities);
    when(mapperToDomain.mapFrom(oneListOfCharacterEntities)).thenReturn(charactersOutput);
    //WHEN
    saveCharactersRepository.save(charactersInput);
    //THEN
    verify(mapperToEntity, times(numberOfInvocationsExpected)).mapFrom(charactersInput);
    verify(repository, times(numberOfInvocationsExpected)).saveAll(oneListOfCharacterEntities);
    verify(mapperToDomain, times(numberOfInvocationsExpected)).mapFrom(oneListOfCharacterEntities);
  }

}