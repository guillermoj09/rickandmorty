package cl.mobdev.rickandmorty.domain.port.output;

import cl.mobdev.rickandmorty.domain.model.Location;

public interface GetLocationGateway {
  public Location execute(String url);

}
