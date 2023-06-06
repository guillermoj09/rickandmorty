package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.domain.port.input.GetCharacterById;
import cl.mobdev.rickandmorty.domain.port.output.GetCharacterGateway;
import cl.mobdev.rickandmorty.domain.port.output.GetLocationGateway;

public class GetCharacterByIdUseCase implements GetCharacterById {

  private final GetCharacterGateway getCharacterGateway;
  private final GetLocationGateway getLocationGateway;

  public GetCharacterByIdUseCase(GetCharacterGateway getCharacterGateway, GetLocationGateway getLocationGateway) {
    this.getCharacterGateway = getCharacterGateway;
    this.getLocationGateway = getLocationGateway;
  }

  @Override
  public Character execute(int id) {
    Character character = getCharacterGateway.execute(id);

    if (!character.getOrigin().getUrl().equals("")) {
      String url = character.getOrigin().getUrl();
      Location location = this.getLocationGateway.execute(url);
      character.setLocation(location);
    }
    return character;
  }
}
