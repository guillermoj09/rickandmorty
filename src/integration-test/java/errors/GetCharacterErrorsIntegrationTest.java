package errors;

import static cl.mobdev.rickandmorty.TestUtil.jsonToHashmap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.infraestructure.exception.NotFounException;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientCharacter;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientResponse;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetCharacterErrorsIntegrationTest {

  private final String contentTypeExpected = "application/json";
  private MockMvc mockMvc;
  private HttpHeaders headers;

  @MockBean
  private RestTemplate restTemplate;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeEach
  public void setup() {
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  void should_return_404_and_error_response_when_character_api_throw_some_error() throws Exception {
    int statusExpected = 404;
    HashMap responseExpected = jsonToHashmap("{\"message\": \"Error Character RickAndMorty API\"}");

    String url = "https://some-url/character/";
    Exception oneException = new NotFounException("Some message error");
    when(restTemplate.getForEntity(url + 121111, ClientCharacter.class)).thenThrow(oneException);

    //WHEN
    MvcResult mvcResult = this.mockMvc
        .perform(MockMvcRequestBuilders
            .get("/character/{id}", 121111)
            .headers(headers))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();

    HashMap response = jsonToHashmap(mvcResult.getResponse().getContentAsString());

    assertThat(mvcResult.getResponse().getStatus()).isEqualTo(statusExpected);
    assertThat(mvcResult.getResponse().getContentType()).isEqualTo(contentTypeExpected);

  }


}
