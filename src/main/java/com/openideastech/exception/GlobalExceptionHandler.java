package com.openideastech.exception;

import com.openideastech.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DuplicateResourceException.class)
  public ResponseEntity<ErrorResponseDTO> handleDuplicateResource(DuplicateResourceException e) {
    ErrorResponseDTO error = new ErrorResponseDTO(
      HttpStatus.CONFLICT.value(),
      e.getMessage()
    );

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }
}
