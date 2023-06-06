package cl.mobdev.rickandmorty.infraestructure.controller.exception;

import cl.mobdev.rickandmorty.api.model.ApiErrorResponse;
import cl.mobdev.rickandmorty.infraestructure.exception.CharacterException;
import cl.mobdev.rickandmorty.infraestructure.exception.GatewayException;
import cl.mobdev.rickandmorty.infraestructure.exception.NoSaveException;
import cl.mobdev.rickandmorty.infraestructure.exception.NotFounException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

  @ResponseStatus(HttpStatus.BAD_GATEWAY)
  @ExceptionHandler(GatewayException.class)
  public ResponseEntity<ApiErrorResponse> handleError(GatewayException ex) {
    return new ResponseEntity<>(new ApiErrorResponse().message(ex.getMessage()), HttpStatus.BAD_GATEWAY);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ApiErrorResponse> handleError(RuntimeException ex) {
    System.out.println("entro a 500");
    return new ResponseEntity<>(new ApiErrorResponse().message(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFounException.class)
  public ResponseEntity<ApiErrorResponse> handleError(NotFounException ex) {
    return new ResponseEntity<>(new ApiErrorResponse().message(ex.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(NoSaveException.class)
  public ResponseEntity<ApiErrorResponse> handleError(NoSaveException ex) {
    System.out.println("entro a 400");
    return new ResponseEntity<>(new ApiErrorResponse().message(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(CharacterException.class)
  public ResponseEntity<ApiErrorResponse> handleError(CharacterException ex) {
    System.out.println("entro a 500 character exepcion");
    return new ResponseEntity<>(new ApiErrorResponse().message(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }



}
