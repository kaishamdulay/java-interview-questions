package com.collective.backbase.health.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import com.collective.backbase.health.constants.Enums.HEALTH_STATUS;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DependencyHealthResponse {

  private String entity;
  private String cause;
  private HEALTH_STATUS healthStatus;
}
