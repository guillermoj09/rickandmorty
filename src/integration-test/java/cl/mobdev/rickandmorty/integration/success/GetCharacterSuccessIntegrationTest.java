package cl.mobdev.rickandmorty.integration.success;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import cl.mobdev.rickandmorty.TestUtil;
import cl.mobdev.rickandmorty.api.model.GetCharacterResponse;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientCharacter;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientLocation;
import cl.mobdev.rickandmorty.mocks.ClientCharacterMock;
import cl.mobdev.rickandmorty.mocks.ClientLocationMock;
import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetCharacterSuccessIntegrationTest {

  private final String contentTypeExpected = "application/json";
  private MockMvc mockMvc;
  private HttpHeaders headers;

  @MockBean
  private RestTemplate restTemplate;
  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeEach
  public void setup(WebApplicationContext webApplicationContext) {
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  void should_return_200_and_valid_response() throws Exception {
    int statusExpected = 200;
    GetCharacterResponse responseExpected = buildObjectFromFile();
    int idUrl = 1;
    //GIVEN
    String urlCharacterApi = "https://some-url/character/1";
    ClientCharacter clientCharacter = ClientCharacterMock.build_only();
    ResponseEntity responseOfCharacterApiWithOk = new ResponseEntity(clientCharacter, HttpStatus.OK);
    when(restTemplate.getForEntity(urlCharacterApi, ClientCharacter.class)).thenReturn(responseOfCharacterApiWithOk);


    ClientLocation clientLocation = ClientLocationMock.build_only();
    ResponseEntity responseEntityWithOk = new ResponseEntity<>(clientLocation, HttpStatus.OK);
    when(restTemplate.getForEntity("https://some-url/location/1", ClientLocation.class)).thenReturn(responseEntityWithOk);

    //WHEN
    MvcResult mvcResult = this.mockMvc
        .perform(MockMvcRequestBuilders
            .get("/character/1")
            .headers(headers))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
    mvcResult.getResponse().getContentAsString();
    //GetCharacterResponse response = buildObjectFromString(mvcResult.getResponse().getContentAsString());

    //THEN
    assertThat(mvcResult.getResponse().getStatus()).isEqualTo(statusExpected);
    assertThat(mvcResult.getResponse().getContentType()).isEqualTo(contentTypeExpected);
    //assertThat(response).isEqualTo(responseExpected);
  }


  private GetCharacterResponse buildObjectFromFile() {
    String jsonFile = "getCharacterResponse.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<GetCharacterResponse>() {
    });
  }

  private GetCharacterResponse buildObjectFromString(String json) {
    return TestUtil.buildObjectFromString(json, new TypeReference<GetCharacterResponse>() {
    });
  }



}
