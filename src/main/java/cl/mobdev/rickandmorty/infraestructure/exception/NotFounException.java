package cl.mobdev.rickandmorty.infraestructure.exception;

public class NotFounException extends RuntimeException {

  public NotFounException(String message) {
    super(message);
  }
}
