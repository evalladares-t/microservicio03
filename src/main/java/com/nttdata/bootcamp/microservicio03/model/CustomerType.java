package com.nttdata.bootcamp.microservicio03.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerType {
  PERSONAL("PERSONAL", true),
  BUSINESS("BUSINESS", true);

  private final String description;
  private final boolean isActive;
}
