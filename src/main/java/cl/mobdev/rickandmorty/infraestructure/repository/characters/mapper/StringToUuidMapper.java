package cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class StringToUuidMapper {

  public UUID mapFrom(String id) {
    UUID sameUuid = UUID.fromString(id);
    return sameUuid;
  }
}
