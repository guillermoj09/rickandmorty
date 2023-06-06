package cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa;

import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterEntityRepository extends JpaRepository<CharacterEntity, UUID> {

  Optional<CharacterEntity> findByName(String name);


}
