package com.zonainmueble.services;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.zonainmueble.exceptions.BaseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StorageService {
  @Value("${azure.storage.container-name}")
  private String containerName;

  @Value("${azure.storage.connection-string}")
  private String connectionString;

  public String storePDF(byte[] fileContent, String name) {
    try {
      BlobClientBuilder builder = new BlobClientBuilder()
          .connectionString(connectionString)
          .containerName(containerName)
          .blobName(name);

      BlobHttpHeaders headers = new BlobHttpHeaders().setContentType("application/pdf");

      builder.buildClient().upload(
          new ByteArrayInputStream(fileContent),
          fileContent.length,
          true);

      builder.buildClient().setHttpHeaders(headers);
      return builder.buildClient().getBlobUrl();

    } catch (Exception e) {
      log.error(e.getMessage(), e);
      log.error("Error uploading the file: {}", name);
      throw new BaseException("FAILED_TO_GENERATE_REPORT", "Failed to generate report");
    }
  }
}