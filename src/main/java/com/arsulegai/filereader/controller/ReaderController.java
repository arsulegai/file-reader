package com.arsulegai.filereader.controller;

import com.arsulegai.filereader.service.reader.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReaderController {
  @Autowired private ReaderService readerService;

  @PostMapping(value = "/reader/refresh")
  public boolean triggerFileRead() {
    return readerService.readFiles();
  }
}
