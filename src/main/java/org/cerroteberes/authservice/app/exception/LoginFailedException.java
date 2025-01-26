package org.cerroteberes.authservice.app.exception;

public class LoginFailedExeception extends RuntimeException {
  public LoginFailedExeception(String message) {
    super(message);
  }
}
