package com.nttdata.bootcamp.microservicio03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Microservicio03Application {

  private static final Logger log = LoggerFactory.getLogger(Microservicio03Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Microservicio03Application.class, args);
  }
}
