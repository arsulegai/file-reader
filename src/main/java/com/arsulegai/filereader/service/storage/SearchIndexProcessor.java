package com.arsulegai.filereader.service.storage;

import com.arsulegai.filereader.model.AppFile;

public interface SearchIndexProcessor {
  /**
   * Process and index the file along with the contents
   *
   * @param file the file to be processed
   * @return the boolean status of processing
   */
  boolean processFile(AppFile file);
}
