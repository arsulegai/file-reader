package com.arsulegai.filereader.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "storage.google", havingValue = "true")
@ConfigurationProperties(prefix = "datasource.google.connection")
@Data
public class GoogleDriveConnectionProperties {
  private String connectionJson;
  private String rootFolder;
}
