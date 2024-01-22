package com.arsulegai.filereader.exception;

public class InvalidConfigurationException extends BaseException {
  public InvalidConfigurationException(ErrorCode code, String message) {
    super(code, message);
  }

  public InvalidConfigurationException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }
}
