package com.arsulegai.filereader.service.storage.impl;

import com.arsulegai.filereader.config.StorageProperties;
import com.arsulegai.filereader.exception.ErrorCode;
import com.arsulegai.filereader.exception.InvalidConfigurationException;
import com.arsulegai.filereader.exception.NoImplementationFoundException;
import com.arsulegai.filereader.model.AppFile;
import com.arsulegai.filereader.service.storage.StorageService;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/** Default implementation for the storage services */
@Service
@ConditionalOnProperty(value = "storage.google", havingValue = "false")
public class StorageServiceImpl implements StorageService {

  @Autowired private StorageProperties storageProperties;

  private void defaultExecution() {
    if (storageProperties.isGoogle()) {
      throw new InvalidConfigurationException(
          ErrorCode.MissingParameters, "google drive is supposed to be configured");
    }
    throw new NoImplementationFoundException(
        ErrorCode.NotImplemented, "only google drive is implemented");
  }

  @Override
  public List<AppFile> listFiles() {
    defaultExecution();
    return null;
  }

  @Override
  public ByteArrayOutputStream readFile(AppFile file) {
    defaultExecution();
    return null;
  }
}
