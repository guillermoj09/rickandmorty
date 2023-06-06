package cl.mobdev.rickandmorty.infraestructure.repository.locations.jpa;

import cl.mobdev.rickandmorty.infraestructure.repository.locations.entity.LocationEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationEntityRepository extends JpaRepository<LocationEntity, UUID> {
}
