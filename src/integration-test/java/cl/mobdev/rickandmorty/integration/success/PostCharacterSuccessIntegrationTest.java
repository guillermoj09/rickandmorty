package cl.mobdev.rickandmorty.integration.success;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import cl.mobdev.rickandmorty.api.model.GetCharactersResponse;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa.CharacterEntityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostCharacterSuccessIntegrationTest {

  private MockMvc mockMvc;
  private HttpHeaders headers;
  @Autowired
  private WebApplicationContext webApplicationContext;
  @Autowired
  private CharacterEntityRepository repository;


  @BeforeEach
  @Sql(statements = "DELETE FROM characters", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  public void setup() {
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    this.mockMvc = MockMvcBuilders
        .webAppContextSetup(this.webApplicationContext)
        .build();
  }

  @Test
  void should_save_character_names_in_database() throws Exception {
    int statusExpected = 201;
    int numberOfCharactersSavedExpected = 1;
    String nameExpected = "Guillermo";

    //GIVEN
    String request = buildRequestFromFile();

    MvcResult mvcResult = this.mockMvc
        .perform(MockMvcRequestBuilders
            .post("/character")
            .content(request)
            .headers(headers))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();

    //WHEN
    int statusResponse = mvcResult.getResponse().getStatus();
    int responseFindByName = 0;
    Optional characterEntity = repository.findByName(nameExpected);

    if (characterEntity.isPresent()) {
      responseFindByName = 1;
    }
    //THEN
    assertEquals(statusExpected, statusResponse);
    assertEquals(numberOfCharactersSavedExpected, responseFindByName);
  }

  private String buildRequestFromFile() throws IOException {
    File json = ResourceUtils.getFile("classpath:character_response.json");
    ObjectMapper mapper = new ObjectMapper();
    ApiCharacter object = mapper.readValue(json, ApiCharacter.class);
    return mapper.writeValueAsString(object);
  }

}
