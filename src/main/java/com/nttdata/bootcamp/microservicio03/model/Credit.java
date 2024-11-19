package com.nttdata.bootcamp.microservicio03.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credit")
public class Credit {
  @Id private String id = UUID.randomUUID().toString();
  private String creditNumber;
  private String customer;
  private CreditType creditType;
  private String currency;
  private BigDecimal creditLimit;
  private BigDecimal amountAvailable;
  private CardBank cardBank;
  private Boolean active;
}
