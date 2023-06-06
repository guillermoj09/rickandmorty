package cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringToUuidMapperTest {

  private StringToUuidMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new StringToUuidMapper();
  }
  @Test
  void should_uuid_from_string() {
    final String id = "76fb43a-0e83-46b2-8753-7903ef3ef168";
    UUID uuidExpected = UUID.fromString("76fb43a-0e83-46b2-8753-7903ef3ef168");

    UUID idChange = mapper.mapFrom(id);

    assertEquals(uuidExpected,idChange);

  }

}