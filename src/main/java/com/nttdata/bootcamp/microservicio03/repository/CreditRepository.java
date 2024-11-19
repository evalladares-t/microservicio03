package com.nttdata.bootcamp.microservicio03.repository;

import com.nttdata.bootcamp.microservicio03.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {}
