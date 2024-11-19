package com.nttdata.bootcamp.microservicio03.expose;

import com.nttdata.bootcamp.microservicio03.model.Credit;
import com.nttdata.bootcamp.microservicio03.service.CreditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("api/v1/credit")
public class CreditController {

  private CreditService creditService;

  public CreditController(CreditService creditService) {
    this.creditService = creditService;
  }

  @GetMapping({"/{id}/", "/{id}"})
  public Mono<Credit> findbyId(@PathVariable("id") String creditId) {
    log.info("Find by id a credit in the controller.");
    return creditService.findById(creditId);
  }

  @GetMapping({"", "/"})
  public Flux<Credit> findAll() {
    log.info("List all credits in the controller.");
    return creditService.findAll();
  }

  @PostMapping({"", "/"})
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Credit> create(@RequestBody Credit credit) {
    log.info("Create an credit in the controller.");
    return creditService.create(credit);
  }

  @PutMapping({"/{id}/", "/{id}"})
  public Mono<ResponseEntity<Credit>> update(
      @RequestBody Credit credit, @PathVariable("id") String creditId) {
    log.info("Update an credit in the controller.");
    return creditService
        .update(credit, creditId)
        .flatMap(creditUpdate -> Mono.just(ResponseEntity.ok(creditUpdate)))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @PatchMapping({"/{id}/", "/{id}"})
  public Mono<ResponseEntity<Credit>> change(
      @RequestBody Credit credit, @PathVariable("id") String creditId) {
    log.info("Change an credit in the controller.");
    return creditService
        .change(credit, creditId)
        .flatMap(creditUpdate -> Mono.just(ResponseEntity.ok(creditUpdate)))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @DeleteMapping({"/{id}/", "/{id}"})
  public Mono<ResponseEntity<Credit>> delete(@PathVariable("id") String id) {
    log.info("Delete an account in the controller.");
    return creditService
        .remove(id)
        .flatMap(credit -> Mono.just(ResponseEntity.ok(credit)))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }
}
