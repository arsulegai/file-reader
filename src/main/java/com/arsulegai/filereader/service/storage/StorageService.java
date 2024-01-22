package com.arsulegai.filereader.service.storage;

import com.arsulegai.filereader.model.AppFile;
import java.io.ByteArrayOutputStream;
import java.util.List;

public interface StorageService {
  /**
   * List files iteratively from a folder/sub-folder for each of the connecting drive.
   *
   * @return list of files with their path
   */
  List<AppFile> listFiles();

  /**
   * Read a specified file
   *
   * @param file file to read
   * @return the byte array output stream
   */
  ByteArrayOutputStream readFile(AppFile file);
}
