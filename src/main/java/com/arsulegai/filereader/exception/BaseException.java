package com.arsulegai.filereader.exception;

public class BaseException extends RuntimeException {
  private final ErrorCode code;

  public BaseException(ErrorCode code, String message) {
    super(message);
    this.code = code;
  }

  public BaseException(ErrorCode code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public ErrorCode getCode() {
    return code;
  }
}
