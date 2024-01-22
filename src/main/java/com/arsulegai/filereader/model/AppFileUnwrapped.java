package com.arsulegai.filereader.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@Document(indexName = "files")
public class AppFileUnwrapped {
  @Id private String id;
  private AppFile appFile;
  private String content;
}
