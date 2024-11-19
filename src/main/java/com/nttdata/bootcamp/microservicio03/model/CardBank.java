package com.nttdata.bootcamp.microservicio03.model;

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
  private String yearExpire;
  private String monthExpire;
  private String cvv;
  private Boolean active;
}
