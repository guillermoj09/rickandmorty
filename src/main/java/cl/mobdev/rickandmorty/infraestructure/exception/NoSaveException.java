package cl.mobdev.rickandmorty.infraestructure.exception;

public class NoSaveException extends RuntimeException {

  public NoSaveException(String message) {
    super(message);
  }
}
