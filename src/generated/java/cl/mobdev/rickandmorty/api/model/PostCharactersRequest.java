package cl.mobdev.rickandmorty.api.model;

import java.util.Objects;
import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
/**
 * PostCharactersRequest
 */

public class PostCharactersRequest   {
  @JsonProperty("characters")
  private List<ApiCharacter> characters = new ArrayList<ApiCharacter>();

  public PostCharactersRequest characters(List<ApiCharacter> characters) {
    this.characters = characters;
    return this;
  }

  public PostCharactersRequest addCharactersItem(ApiCharacter charactersItem) {
    this.characters.add(charactersItem);
    return this;
  }

   /**
   * Get characters
   * @return characters
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public List<ApiCharacter> getCharacters() {
    return characters;
  }

  public void setCharacters(List<ApiCharacter> characters) {
    this.characters = characters;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostCharactersRequest postCharactersRequest = (PostCharactersRequest) o;
    return Objects.equals(this.characters, postCharactersRequest.characters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(characters);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostCharactersRequest {\n");
    
    sb.append("    characters: ").append(toIndentedString(characters)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

