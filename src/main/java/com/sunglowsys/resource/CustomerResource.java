package com.sunglowsys.resource;

import com.sunglowsys.domain.Customer;
import com.sunglowsys.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerResource {
    private final Logger logger = LoggerFactory.getLogger(CustomerResource.class);
    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/customers")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        logger.debug("Request to save the Customer:{}",customer);
        Customer result = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PutMapping("/customers")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        logger.debug("Request to update the Customer:{}",customer);
        if (customer.getId()==null) {
            throw new RuntimeException("id must not be null");
        }
        Customer result = customerService.update(customer);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/customers")
    public ResponseEntity<Page<Customer>> findAllCustomer(Pageable pageable) {
        logger.debug("Request to findAll the Customer:{}",pageable);
        Page<Customer> result = customerService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<Optional<Customer>> findOneCustomer(@PathVariable Long id) {
        logger.debug("Request to findOne the customer:{}",id);
        Optional<Customer> result = customerService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        logger.debug("Request to delete the Customer:{}",id);
        customerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
