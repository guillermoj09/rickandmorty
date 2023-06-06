package errors;

import static cl.mobdev.rickandmorty.TestUtil.jsonToHashmap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.SaveCharacterRepository;
import cl.mobdev.rickandmorty.infraestructure.exception.NoSaveException;
import cl.mobdev.rickandmorty.infraestructure.exception.NotFounException;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientCharacter;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.entity.CharacterEntity;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.jpa.CharacterEntityRepository;
import cl.mobdev.rickandmorty.infraestructure.repository.characters.mapper.CharacterToCharacterEntityMapper;
import cl.mobdev.rickandmorty.mocks.CharacterEntityMock;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
class PostCharacterErrorIntegrationTest {

  private final String contentTypeExpected = "application/json";
  private MockMvc mockMvc;
  private HttpHeaders headers;

  @Autowired
  private CharacterEntityRepository repository;

  @MockBean
  private CharacterToCharacterEntityMapper characterToCharacterEntityMapper;

  @Mock
  private SaveCharacterRepository saveCharacterRepository;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeEach
  public void setup() {
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  void should_return_500_when_throw_some_error() throws Exception {

    int statusExpected = 500;
    HashMap responseExpected = jsonToHashmap("{\"message\": \"Not Save Character\"}");


    //NoSaveException oneException = new NoSaveException("Some message error");
    String request = buildRequestFromFile();
    //when(characterToCharacterEntityMapper.mapFrom(Mockito.any())).thenThrow(new RuntimeException());

    //WHEN
    MvcResult mvcResult = this.mockMvc
        .perform(MockMvcRequestBuilders
            .post("/character")
            .content(request)
            .headers(headers))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();

    System.out.println(mvcResult.getResponse().getStatus());


    HashMap response = jsonToHashmap(mvcResult.getResponse().getContentAsString());


    assertThat(mvcResult.getResponse().getStatus()).isEqualTo(statusExpected);
    assertThat(mvcResult.getResponse().getContentType()).isEqualTo(contentTypeExpected);

  }

  private String buildRequestFromFile() throws IOException {
    File json = ResourceUtils.getFile("classpath:character_bad_request.json");
    ObjectMapper mapper = new ObjectMapper();
    ApiCharacter object = mapper.readValue(json, ApiCharacter.class);
    return mapper.writeValueAsString(object);
  }

}
