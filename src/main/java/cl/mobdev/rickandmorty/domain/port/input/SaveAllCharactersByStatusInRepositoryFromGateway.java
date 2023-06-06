package cl.mobdev.rickandmorty.domain.port.input;

public interface SaveAllCharactersByStatusInRepositoryFromGateway {

  int execute(String status);
}
