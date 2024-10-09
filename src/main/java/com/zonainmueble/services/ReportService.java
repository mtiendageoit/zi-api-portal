package com.zonainmueble.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.zonainmueble.clients.ReportsApiClient;
import com.zonainmueble.dtos.ReportRequest;
import com.zonainmueble.dtos.ReportResponse;
import com.zonainmueble.enums.ReportType;

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
    String url = storageService.storePDF(pdf, name);

    input.setType(type);
    return ReportResponse.builder()
        .resource(url)
        .request(input)
        .build();
  }

  private String generateReportName() {
    return UUID.randomUUID().toString().replace("-", "") + ".pdf";
  }

}
