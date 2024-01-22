package com.arsulegai.filereader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FileReaderApplication {
  public static void main(String[] args) {
    SpringApplication.run(FileReaderApplication.class, args);
  }
}
