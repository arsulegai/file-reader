package com.arsulegai.filereader.repository;

import com.arsulegai.filereader.model.AppFileUnwrapped;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppFilesRepository extends ElasticsearchRepository<AppFileUnwrapped, String> {}
