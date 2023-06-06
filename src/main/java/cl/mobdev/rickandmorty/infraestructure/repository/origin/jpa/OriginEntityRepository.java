package cl.mobdev.rickandmorty.infraestructure.repository.origin.jpa;

import cl.mobdev.rickandmorty.infraestructure.repository.origin.entity.OriginEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OriginEntityRepository extends JpaRepository<OriginEntity, UUID> {

}
