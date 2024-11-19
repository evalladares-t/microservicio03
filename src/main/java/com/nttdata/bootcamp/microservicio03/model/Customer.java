package com.nttdata.bootcamp.microservicio03.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

  private String id;
  private CustomerType customerType;
  private String firstName;
  private String lastName;
  private Boolean isActive;
}
