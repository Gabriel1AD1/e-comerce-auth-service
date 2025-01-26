package org.cerroteberes.authservice.app.exception;

public class FailedCreateRefreshTokenException extends RuntimeException {
  public FailedCreateRefreshTokenException(String message) {
    super(message);
  }
}
