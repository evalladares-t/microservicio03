package com.nttdata.bootcamp.microservicio03.service.impl;

import com.nttdata.bootcamp.microservicio03.model.Credit;
import com.nttdata.bootcamp.microservicio03.repository.CreditRepository;
import com.nttdata.bootcamp.microservicio03.service.CreditService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {

  private CreditRepository creditRepository;

  public CreditServiceImpl(CreditRepository creditRepository) {
    this.creditRepository = creditRepository;
  }

  @Override
  public Mono<Credit> create(Credit credit) {
    return null;
  }

  @Override
  public Mono<Credit> findById(String creditIdId) {
    return null;
  }

  @Override
  public Flux<Credit> findAll() {
    return null;
  }

  @Override
  public Mono<Credit> update(Credit credit, String creditIdId) {
    return null;
  }

  @Override
  public Mono<Credit> change(Credit credit, String creditIdId) {
    return null;
  }

  @Override
  public Mono<Credit> remove(String creditIdId) {
    return null;
  }
}
