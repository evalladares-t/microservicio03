package com.nttdata.bootcamp.microservicio03.service;

import com.nttdata.bootcamp.microservicio03.model.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {

  Mono<Credit> create(Credit credit);

  Mono<Credit> findById(String creditIdId);

  Flux<Credit> findAll();

  Mono<Credit> update(Credit credit, String creditIdId);

  Mono<Credit> change(Credit credit, String creditIdId);

  Mono<Credit> remove(String creditIdId);

  Flux<Credit> findByCustomerId(String id);
}
