package com.zonainmueble.dtos;

import com.zonainmueble.enums.ReportType;
import com.zonainmueble.validations.ValidCoordinates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ValidCoordinates
public class ReportRequest {
  @NotBlank
  private String address;

  @NotNull
  private Double longitude;

  @NotNull
  private Double latitude;

  @NotNull
  private String user;

  private ReportType type;
}
