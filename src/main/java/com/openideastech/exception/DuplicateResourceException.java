package com.openideastech.exception;

public class DuplicateResourceException extends RuntimeException {

  public DuplicateResourceException(String resource) {
    super(resource);
  }

}
