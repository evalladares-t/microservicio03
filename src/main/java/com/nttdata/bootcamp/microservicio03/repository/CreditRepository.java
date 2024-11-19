package com.nttdata.bootcamp.microservicio03.repository;

import com.nttdata.bootcamp.microservicio03.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {

  Flux<Credit> findByCustomerId(String customerId);
}
