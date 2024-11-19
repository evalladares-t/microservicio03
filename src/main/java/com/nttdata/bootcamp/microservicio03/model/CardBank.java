package com.nttdata.bootcamp.microservicio03.model;

import java.time.YearMonth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardBank {

  private String cardNumber;
  private YearMonth expireDate;
  private String cvv;
}
