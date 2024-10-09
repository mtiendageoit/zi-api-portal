package com.zonainmueble.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zonainmueble.dtos.ReportRequest;
import com.zonainmueble.dtos.ReportResponse;
import com.zonainmueble.enums.ReportType;
import com.zonainmueble.services.ReportService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {
  private final ReportService reportService;

  @PostMapping
  public ReportResponse report(@RequestParam ReportType type, @Valid @RequestBody ReportRequest input) {
    log.info("Processing report type: {}, input: {}", type, input);
    return reportService.report(input, type);
  }
}
