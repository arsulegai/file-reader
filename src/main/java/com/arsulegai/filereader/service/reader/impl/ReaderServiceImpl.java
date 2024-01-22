package com.arsulegai.filereader.service.reader.impl;

import com.arsulegai.filereader.model.AppFile;
import com.arsulegai.filereader.service.reader.ReaderService;
import com.arsulegai.filereader.service.storage.SearchIndexProcessor;
import com.arsulegai.filereader.service.storage.StorageService;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Serves the needs of the controllers and other scheduling logic of the reader service. */
@Service
@Slf4j
public class ReaderServiceImpl implements ReaderService {

  @Autowired private StorageService storageService;
  @Autowired private SearchIndexProcessor searchIndexProcessor;

  @Override
  public boolean readFiles() {
    // TODO: process these files asynchronously, do not block the API
    // list the files from the storage service
    List<AppFile> files = storageService.listFiles();
    for (AppFile file : files) {
      ByteArrayOutputStream outputStream = storageService.readFile(file);
      // process the outputstream
      // send this to the elasticsearch for indexing
      // TODO: assume the file is UTF-8 encoded
      String fileContent = new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
      log.info("processed the file {}", file);
      if (!searchIndexProcessor.processFile(file)) {
        return false;
      }
    }
    // success
    return true;
  }
}
