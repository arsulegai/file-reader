package com.arsulegai.filereader.service.storage.impl;

import com.arsulegai.filereader.config.GoogleDriveConnectionProperties;
import com.arsulegai.filereader.exception.ErrorCode;
import com.arsulegai.filereader.exception.GoogleDriveIOException;
import com.arsulegai.filereader.model.AppFile;
import com.arsulegai.filereader.service.storage.StorageService;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "storage.google", havingValue = "true")
public class GoogleStorageServiceImpl implements StorageService {

  @Autowired private Drive drive;
  @Autowired private GoogleDriveConnectionProperties googleDriveConnectionProperties;

  private List<File> listRecursively(String fileDescriptor) {
    List<File> files;
    // TODO: assume that all files are txt and utf-8
    try {
      files =
          drive
              .files()
              .list()
              .setQ("'" + googleDriveConnectionProperties.getRootFolder() + "' in parents")
              .execute()
              .getFiles();
      for (File file : files) {
        if (file.getMimeType().equals("application/vnd.google-apps.folder")) {
          List<File> nestedFiles = listRecursively(file.getId());
          files.addAll(nestedFiles);
        }
      }
    } catch (IOException e) {
      throw new GoogleDriveIOException(
          ErrorCode.GoogleDriveIOError, "unable to list files and folders", e);
    }
    return files;
  }

  public String getFilePath(File file) throws IOException {
    List<String> pathComponents = getFileParents(file);
    pathComponents.add(file.getName());
    return String.join("/", pathComponents);
  }

  private List<String> getFileParents(File file) throws IOException {
    List<String> pathComponents = new ArrayList<>();
    while (file.getParents() != null && !file.getParents().isEmpty()) {
      // throw if more than one parent is found
      if (file.getParents().size() > 1) {
        throw new GoogleDriveIOException(
            ErrorCode.GoogleDriveMoreThanOneParentError, "more than one parent");
      }
      String parentId = file.getParents().get(0); // Assuming a file has only one parent
      file = drive.files().get(parentId).execute();
      pathComponents.add(file.getName());
    }
    return pathComponents;
  }

  @Override
  public List<AppFile> listFiles() {
    List<File> files = listRecursively(googleDriveConnectionProperties.getRootFolder());
    return files.stream()
        .map(
            file -> {
              AppFile appFile;
              try {
                // TODO: alternatively follow the object from pattern
                appFile =
                    AppFile.builder()
                        .name(file.getName())
                        .path(getFilePath(file))
                        .id(file.getId())
                        .build();
              } catch (IOException e) {
                throw new GoogleDriveIOException(
                    ErrorCode.GoogleDriveIOError, "unable to fetch drive information", e);
              }
              return appFile;
            })
        .collect(Collectors.toList());
  }

  @Override
  public ByteArrayOutputStream readFile(AppFile file) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      drive.files().get(file.getId()).executeMediaAndDownloadTo(outputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return outputStream;
  }
}
