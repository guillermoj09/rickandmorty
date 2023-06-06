package cl.mobdev.rickandmorty.domain.port.input;

public interface DeleteCharacterInRepository {

  int execute(String id);
}
