package com.zonainmueble.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ErrorResponse> baseException(BaseException ex) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getCode(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
