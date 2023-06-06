package cl.mobdev.rickandmorty.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
/**
 * ApiOrigin
 */

public class ApiOrigin   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("url")
  private String url = null;

  @JsonProperty("dimension")
  private String dimension = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("residents")
  private List<String> residents = new ArrayList<String>();

  public ApiOrigin name(String name) {
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

  public ApiOrigin url(String url) {
    this.url = url;
    return this;
  }

   /**
   * Get url
   * @return url
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ApiOrigin dimension(String dimension) {
    this.dimension = dimension;
    return this;
  }

   /**
   * Get dimension
   * @return dimension
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getDimension() {
    return dimension;
  }

  public void setDimension(String dimension) {
    this.dimension = dimension;
  }

  public ApiOrigin type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ApiOrigin residents(List<String> residents) {
    this.residents = residents;
    return this;
  }

  public ApiOrigin addResidentsItem(String residentsItem) {
    this.residents.add(residentsItem);
    return this;
  }

   /**
   * Get residents
   * @return residents
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public List<String> getResidents() {
    return residents;
  }

  public void setResidents(List<String> residents) {
    this.residents = residents;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiOrigin apiOrigin = (ApiOrigin) o;
    return Objects.equals(this.name, apiOrigin.name) &&
        Objects.equals(this.url, apiOrigin.url) &&
        Objects.equals(this.dimension, apiOrigin.dimension) &&
        Objects.equals(this.type, apiOrigin.type) &&
        Objects.equals(this.residents, apiOrigin.residents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, url, dimension, type, residents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiOrigin {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    dimension: ").append(toIndentedString(dimension)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    residents: ").append(toIndentedString(residents)).append("\n");
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

