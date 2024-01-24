package com.arsulegai.filereader.service.reader.impl;

import com.arsulegai.filereader.model.AppFile;
import com.arsulegai.filereader.service.reader.ReaderService;
import com.arsulegai.filereader.service.storage.SearchIndexProcessor;
import com.arsulegai.filereader.service.storage.StorageService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Serves the needs of the controllers and other scheduling logic of the reader service. */
@Service
@Slf4j
public class ReaderServiceImpl implements ReaderService {

  @Autowired private StorageService storageService;
  @Autowired private SearchIndexProcessor searchIndexProcessor;

  @Override
  @Transactional
  @Scheduled(fixedRate = 5000)
  public boolean readFiles() {
    // TODO: process these files asynchronously, do not block the API
    // list the files from the storage service
    List<AppFile> files = storageService.listFiles();
    for (AppFile file : files) {
      // process the outputstream
      // send this to the elasticsearch for indexing
      log.info("processed the file {}", file);
      if (!searchIndexProcessor.processFile(file)) {
        return false;
      }
    }
    // success
    return true;
  }
}
