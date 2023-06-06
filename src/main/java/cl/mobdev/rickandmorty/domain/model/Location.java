package cl.mobdev.rickandmorty.domain.model;

import java.util.List;

public class Location {

  private Integer id;
  private String name;
  private String type;
  private String dimension;

  private List<String> residents;

  private String url;

  private String uuid;


  public static Location.LocationBuilder builder() {
    return new Location.LocationBuilder();
  }

  public List<String> getResidents() {
    return residents;
  }

  public void setResidents(List<String> residents) {
    this.residents = residents;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDimension() {
    return dimension;
  }

  public void setDimension(String dimension) {
    this.dimension = dimension;
  }

  public static final class LocationBuilder {
    private Integer id;
    private String name;
    private String type;
    private String dimension;
    private List<String> residents;
    private String url;

    private String uuid;

    private LocationBuilder() {
    }

    public Location.LocationBuilder withId(Integer id) {
      this.id = id;
      return this;
    }

    public Location.LocationBuilder withUuid(String uuid) {
      this.uuid = uuid;
      return this;
    }

    public Location.LocationBuilder withResidents(List<String> residents) {
      this.residents = residents;
      return this;
    }


    public Location.LocationBuilder withUrl(String url) {
      this.url = url;
      return this;
    }

    public Location.LocationBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public Location.LocationBuilder withType(String type) {
      this.type = type;
      return this;
    }

    public Location.LocationBuilder withDimension(String dimension) {
      this.dimension = dimension;
      return this;
    }

    public Location build() {
      Location location = new Location();
      location.setId(this.id);
      location.setName(this.name);
      location.setType(this.type);
      location.setDimension(this.dimension);
      location.setUrl(this.url);
      location.setResidents(this.residents);
      location.setUuid(this.uuid);
      return location;
    }

  }

}
