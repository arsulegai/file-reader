package com.arsulegai.filereader.service.storage.impl;

import com.arsulegai.filereader.model.AppFile;
import com.arsulegai.filereader.model.AppFileUnwrapped;
import com.arsulegai.filereader.repository.AppFilesRepository;
import com.arsulegai.filereader.service.storage.SearchIndexProcessor;
import com.arsulegai.filereader.service.storage.StorageService;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ElasticSearchIndexProcessorImpl implements SearchIndexProcessor {
  @Autowired private StorageService storageService;
  @Autowired private AppFilesRepository appFilesRepository;

  @Override
  @Transactional
  public boolean processFile(AppFile file) {
    ByteArrayOutputStream outputStream = storageService.readFile(file);
    // TODO: assume the file is UTF-8 encoded
    String fileContent = new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    AppFileUnwrapped appFileUnwrapped =
        AppFileUnwrapped.builder()
            .appFile(file)
            .content(fileContent)
            .id(file.getUniqueId())
            .build();
    appFilesRepository.save(appFileUnwrapped);
    return false;
  }
}
