package cl.mobdev.rickandmorty.api.model;

import java.util.Objects;
import cl.mobdev.rickandmorty.api.model.ApiOrigin;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * GetCharacterResponse
 */

public class GetCharacterResponse   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("species")
  private String species = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("episode_count")
  private Integer episodeCount = null;

  @JsonProperty("origin")
  private ApiOrigin origin = null;

  public GetCharacterResponse id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public GetCharacterResponse name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GetCharacterResponse status(String status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public GetCharacterResponse species(String species) {
    this.species = species;
    return this;
  }

   /**
   * Get species
   * @return species
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public GetCharacterResponse type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public GetCharacterResponse episodeCount(Integer episodeCount) {
    this.episodeCount = episodeCount;
    return this;
  }

   /**
   * Get episodeCount
   * @return episodeCount
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getEpisodeCount() {
    return episodeCount;
  }

  public void setEpisodeCount(Integer episodeCount) {
    this.episodeCount = episodeCount;
  }

  public GetCharacterResponse origin(ApiOrigin origin) {
    this.origin = origin;
    return this;
  }

   /**
   * Get origin
   * @return origin
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public ApiOrigin getOrigin() {
    return origin;
  }

  public void setOrigin(ApiOrigin origin) {
    this.origin = origin;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCharacterResponse getCharacterResponse = (GetCharacterResponse) o;
    return Objects.equals(this.id, getCharacterResponse.id) &&
        Objects.equals(this.name, getCharacterResponse.name) &&
        Objects.equals(this.status, getCharacterResponse.status) &&
        Objects.equals(this.species, getCharacterResponse.species) &&
        Objects.equals(this.type, getCharacterResponse.type) &&
        Objects.equals(this.episodeCount, getCharacterResponse.episodeCount) &&
        Objects.equals(this.origin, getCharacterResponse.origin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, status, species, type, episodeCount, origin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetCharacterResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    species: ").append(toIndentedString(species)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    episodeCount: ").append(toIndentedString(episodeCount)).append("\n");
    sb.append("    origin: ").append(toIndentedString(origin)).append("\n");
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

