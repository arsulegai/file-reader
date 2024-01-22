package com.arsulegai.filereader.exception;

public class GoogleDriveIOException extends BaseException {
  public GoogleDriveIOException(ErrorCode code, String message) {
    super(code, message);
  }

  public GoogleDriveIOException(ErrorCode code, String message, Throwable cause) {
    super(code, message, cause);
  }
}
