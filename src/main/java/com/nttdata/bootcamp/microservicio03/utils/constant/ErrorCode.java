package com.nttdata.bootcamp.microservicio03.utils.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  DATA_NOT_FOUND("404", "Data not found"),
  CARD_MISSING("400", "the card is required for this product"),
  INVALID_REQUEST("400", "Invalid request parameters"),
  CREDIT_NO_CREATED("404", "The credit was not created"),
  CREDIT_TYPE_ALREADY("404", "The client already has an credit of this type"),
  CREDIT_NO_UPDATE("404", "The credit was not update"),
  CREDIT_NO_DELETED("404", "The credit was not deleted"),
  CREDIT_NO_COMPLETED("404", "Operación no completada"),
  CREDIT_TYPE_NO_ALLOWED("404", "Account type not allowed for this customer"),

  INTERNAL_SERVER_ERROR("500", "Internal server error"),
  SERVICE_UNAVAILABLE("503", "Service unavailable");

  private final String code;
  private final String message;
}
