package com.arsulegai.filereader.service.reader.impl;

import com.arsulegai.filereader.model.AppFile;
import com.arsulegai.filereader.service.reader.ReaderService;
import com.arsulegai.filereader.service.storage.StorageService;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Serves the needs of the controllers and other scheduling logic of the reader service. */
@Service
public class ReaderServiceImpl implements ReaderService {

  @Autowired private StorageService storageService;

  @Override
  public boolean readFiles() {
    // list the files from the storage service
    List<AppFile> files = storageService.listFiles();
    for (AppFile file : files) {
      ByteArrayOutputStream outputStream = storageService.readFile(file);
      // process the outputstream
      // send this to the elasticsearch for indexing
    }
    // success
    return true;
  }
}
