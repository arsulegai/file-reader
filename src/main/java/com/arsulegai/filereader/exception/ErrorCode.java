package com.arsulegai.filereader.exception;

public enum ErrorCode {

  // @formatter:off
  Undefined(0, "Unknown Error"),
  NotImplemented(1, "no implementation found"),
  MissingParameters(1000, "Input Configuration Error"),
  GoogleDriveIOError(2000, "Error during google drive operations"),
  GoogleDriveMoreThanOneParentError(2001, "More than one parent found");
  // @formatter:on

  private final int value;
  private final String reason;

  ErrorCode(int value, String reason) {
    this.value = value;
    this.reason = reason;
  }

  public int getValue() {
    return value;
  }

  public String getReason() {
    return reason;
  }
}
