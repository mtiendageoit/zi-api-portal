package com.zonainmueble.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.zonainmueble.clients.ReportsApiClient;
import com.zonainmueble.dtos.ReportRequest;
import com.zonainmueble.dtos.ReportResponse;
import com.zonainmueble.enums.ReportType;
import com.zonainmueble.utils.StringUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {
  private final StorageService storageService;
  private final ReportsApiClient reportsApiClient;

  public ReportResponse report(ReportRequest input, ReportType type) {
    // TODO Verificar si el usuario puede generar reportes

    byte[] pdf = reportsApiClient.report(input, type);
    String name = generateReportName();
    Map<String, String> metadata = getMetadata(input, type);
    String url = storageService.storePDF(pdf, name, metadata);

    input.setType(type);
    return ReportResponse.builder()
        .resource(url)
        .request(input)
        .build();
  }

  private String generateReportName() {
    return UUID.randomUUID().toString().replace("-", "") + ".pdf";
  }

  private Map<String, String> getMetadata(ReportRequest input, ReportType type) {
    Map<String, String> metadata = new HashMap<>();

    String address = StringUtils.toUTF8(StringUtils.removeAccents(input.getAddress()));

    metadata.put("address", address);
    metadata.put("longitude", input.getLongitude().toString());
    metadata.put("latitude", input.getLatitude().toString());
    metadata.put("user", input.getUser());
    metadata.put("type", type.name());

    return metadata;
  }
}
