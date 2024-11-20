package com.nttdata.bootcamp.microservicio03.service.impl;

import com.nttdata.bootcamp.microservicio03.model.Credit;
import com.nttdata.bootcamp.microservicio03.model.CreditType;
import com.nttdata.bootcamp.microservicio03.model.Customer;
import com.nttdata.bootcamp.microservicio03.model.CustomerType;
import com.nttdata.bootcamp.microservicio03.repository.CreditRepository;
import com.nttdata.bootcamp.microservicio03.service.CreditService;
import com.nttdata.bootcamp.microservicio03.utils.constant.ErrorCode;
import com.nttdata.bootcamp.microservicio03.utils.exception.OperationNoCompletedException;
import java.lang.reflect.Field;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CreditServiceImpl implements CreditService {

  private CreditRepository creditRepository;

  private WebClient webClientCustomer;

  public CreditServiceImpl(CreditRepository creditRepository, WebClient webClientCustomer) {
    this.creditRepository = creditRepository;
    this.webClientCustomer = webClientCustomer;
  }

  @Override
  public Mono<Credit> create(Credit credit) {
    String customerId = credit.getCustomerId();
    log.info("Create an account in the service.");

    if (customerId.isBlank()) {
      log.warn("Client ID is empty");
      return Mono.empty();
    }

    return findByIdCustomerService(customerId)
        .flatMap(
            customer -> {
              credit.setCustomerId(customer.getId());
              return validateCreateCredit(credit, creditRepository.findByCustomerId(customerId))
                  .flatMap(
                      allowed -> {
                        if (allowed && validateCustomerTypeAndCreditType(credit, customer)) {
                          setCommonCreditProperties(credit, customer);
                          return creditRepository.insert(credit);
                        }
                        return Mono.error(
                            new OperationNoCompletedException(
                                ErrorCode.CREDIT_NO_CREATED.getCode(),
                                ErrorCode.CREDIT_NO_CREATED.getMessage()));
                      });
            });
  }

  private Mono<Boolean> validateCreateCredit(Credit credit, Flux<Credit> existingCredits) {

    if (credit.getCreditType() == CreditType.CARD_BANK) {
      if (credit.getCardBank() != null) {
        return Mono.just(true);
      }
      return Mono.error(
          new OperationNoCompletedException(
              ErrorCode.CARD_MISSING.getCode(), ErrorCode.CARD_MISSING.getMessage()));
    }

    if (credit.getCreditType() == CreditType.PERSONAL) {
      return existingCredits
          .filter(existingCredit -> existingCredit.getCreditType() == CreditType.PERSONAL)
          .hasElements()
          .map(hasPersonalCredit -> !hasPersonalCredit)
          .switchIfEmpty(Mono.just(true));
    }

    return Mono.just(true);
  }

  private Boolean validateCustomerTypeAndCreditType(Credit credit, Customer customer) {
    if (credit.getCreditType().getDescription().equals(CreditType.BUSINESS.getDescription())
        || credit.getCreditType().getDescription().equals(CreditType.PERSONAL.getDescription())) {
      return credit
          .getCreditType()
          .getDescription()
          .equals(customer.getCustomerType().getDescription());
    }
    return true;
  }

  @Override
  public Mono<Credit> findById(String creditIdId) {
    return creditRepository.findById(creditIdId);
  }

  @Override
  public Flux<Credit> findAll() {
    return creditRepository.findAll();
  }

  @Override
  public Mono<Credit> update(Credit credit, String creditIdId) {
    log.info("Update a credit in the service.");
    return creditRepository
        .findById(creditIdId)
        .flatMap(
            customerDB -> {
              credit.setId(customerDB.getId());
              return creditRepository.save(credit);
            })
        .switchIfEmpty(
            Mono.error(
                new OperationNoCompletedException(
                    ErrorCode.CREDIT_NO_UPDATE.getCode(),
                    ErrorCode.CREDIT_NO_UPDATE.getMessage())));
  }

  @Override
  public Mono<Credit> change(Credit credit, String creditIdId) {
    log.info("Change a credit in the service.");
    return creditRepository
        .findById(creditIdId)
        .flatMap(
            entidadExistente -> {
              // Iterar sobre los campos del objeto entidadExistente
              Field[] fields = credit.getClass().getDeclaredFields();
              for (Field field : fields) {
                if ("id".equals(field.getName())) {
                  continue; // Saltar el campo 'id'
                }
                field.setAccessible(true); // Para acceder a campos privados
                try {
                  // Verificar si el valor del campo en entidadParcial no es null
                  Object value = field.get(credit);
                  if (value != null) {
                    // Actualizar el campo correspondiente en entidadExistente
                    ReflectionUtils.setField(field, entidadExistente, value);
                  }
                } catch (IllegalAccessException e) {
                  e.printStackTrace(); // Manejo de errores si hay problemas con la reflexión
                }
              }
              // Guardar la entidad modificada
              return creditRepository.save(entidadExistente);
            })
        .switchIfEmpty(
            Mono.error(
                new OperationNoCompletedException(
                    ErrorCode.CREDIT_NO_UPDATE.getCode(),
                    ErrorCode.CREDIT_NO_UPDATE.getMessage())));
  }

  @Override
  public Mono<Credit> remove(String creditIdId) {
    return creditRepository
        .findById(creditIdId)
        .flatMap(p -> creditRepository.deleteById(p.getId()).thenReturn(p));
  }

  private void setCommonCreditProperties(Credit credit, Customer customer) {
    Random numberRandom = new Random();

    if (credit.getCreditType().equals(CreditType.CARD_BANK)) {
      credit.getCardBank().setCardNumber(generateCardNumber(numberRandom));
    }
    credit.setCurrency("SOLES");
    credit.setBusiness(customer.getCustomerType().equals(CustomerType.BUSINESS));
    credit.setActive(true);
    credit.setAmountAvailable(credit.getCreditLimit());
    credit.setCreditNumber(Long.toString(numberRandom.nextLong()));
  }

  public String generateCardNumber(Random random) {

    StringBuilder cardNumber = new StringBuilder();

    for (int i = 0; i < 4; i++) {
      int block = random.nextInt(9000) + 1000;
      cardNumber.append(block);
      if (i < 3) {
        cardNumber.append("-");
      }
    }

    return cardNumber.toString();
  }

  public Mono<Customer> findByIdCustomerService(String id) {
    log.info("Getting client with id: [{}]", id);
    return this.webClientCustomer
        .get()
        .uri(uriBuilder -> uriBuilder.path("v1/customers/" + id).build())
        .retrieve()
        .bodyToMono(Customer.class);
  }

  @Override
  public Flux<Credit> findByCustomerId(String id) {
    return creditRepository.findByCustomerId(id);
  }
}
