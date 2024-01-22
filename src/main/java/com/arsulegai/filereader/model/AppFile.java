package com.arsulegai.filereader.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppFile {
  private String name;
  private String path;
  private StorageType storageType;
  private String id;

  public enum StorageType {
    UnknownType,
    Google;
  }

  public String getUniqueId() {
    return path + name + id;
  }
}
