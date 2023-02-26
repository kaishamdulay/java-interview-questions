package com.collective.backbase.health.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import com.collective.backbase.health.constants.Enums.HEALTH_STATUS;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthResponse {

  private com.collective.backbase.health.model.ApplicationInfo applicationInfo;
  private HEALTH_STATUS healthStatus;
  private List<DependencyHealthResponse> dependencyHealthResponses;
  private String applicationStartTime;
}
