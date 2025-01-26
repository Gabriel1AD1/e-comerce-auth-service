package org.cerroteberes.authservice.app.exception;

public class TokenExpiredException extends RuntimeException {
  public TokenExpiredException(String message) {
    super(message);
  }
}
