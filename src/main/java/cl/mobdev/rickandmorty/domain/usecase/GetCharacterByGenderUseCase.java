package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.GetCharacterGateway;
import cl.mobdev.rickandmorty.infraestructure.exception.GenderException;
import org.springframework.stereotype.Service;


@Service
public class GetCharacterByGenderUseCase {
  private final GetCharacterGateway getCharacterGateway;

  public GetCharacterByGenderUseCase(GetCharacterGateway getCharacterGateway) {
    this.getCharacterGateway = getCharacterGateway;
  }

  public Character execute(int id) {

    String gender = "Female";
    Character character = getCharacterGateway.execute(id);

    if (!gender.equals(character.getGender())) {
      throw new GenderException("El character no es mujer");
    }
    return character;
  }
}
