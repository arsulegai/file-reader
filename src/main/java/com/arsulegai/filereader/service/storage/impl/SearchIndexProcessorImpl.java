package com.arsulegai.filereader.service.storage.impl;

import com.arsulegai.filereader.model.AppFile;
import com.arsulegai.filereader.service.storage.SearchIndexProcessor;
import org.springframework.stereotype.Service;

@Service
public class SearchIndexProcessorImpl implements SearchIndexProcessor {
  @Override
  public boolean processFile(AppFile file) {
    return false;
  }
}
