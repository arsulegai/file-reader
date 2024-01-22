package com.arsulegai.filereader.exception;

public class NoImplementationFoundException extends BaseException {
  public NoImplementationFoundException(ErrorCode code, String message) {
    super(code, message);
  }

  public NoImplementationFoundException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }
}
