package com.collective.backbase.health.service;

import com.collective.backbase.health.model.DependencyHealthResponse;

public interface HealthCheck {

  DependencyHealthResponse health();

  String entity();
}
