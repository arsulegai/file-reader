package com.arsulegai.filereader.config;

import com.arsulegai.filereader.exception.ErrorCode;
import com.arsulegai.filereader.exception.InvalidConfigurationException;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleDriveConfig {

  @Autowired(required = false)
  private GoogleDriveConnectionProperties googleDriveConnectionProperties;

  // throwing exceptions should be ok, because they are caught at boot time
  private GoogleCredentials getCredentials() throws IOException {
    return GoogleCredentials.fromStream(
            Files.newInputStream(Paths.get(googleDriveConnectionProperties.getConnectionJson())))
        .createScoped(Collections.singletonList(DriveScopes.DRIVE_READONLY));
  }

  private HttpRequestInitializer getRequestInitializer() throws IOException {
    return new HttpCredentialsAdapter(getCredentials());
  }

  @Bean
  @ConditionalOnProperty(name = "storage.google", havingValue = "true")
  public Drive getDrive() throws IOException {
    if (googleDriveConnectionProperties == null) {
      throw new InvalidConfigurationException(
          ErrorCode.MissingParameters, "drive connection parameters are missing");
    }
    return new Drive.Builder(
            new NetHttpTransport(), GsonFactory.getDefaultInstance(), getRequestInitializer())
        .setApplicationName("file-reader")
        .build();
  }

  @Bean
  @ConditionalOnProperty(name = "storage.google", havingValue = "false")
  public Drive getEmptyDrive() {
    return null;
  }
}
