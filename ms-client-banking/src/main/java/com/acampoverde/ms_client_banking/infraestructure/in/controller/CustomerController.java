package com.acampoverde.ms_client_banking.infraestructure.in.controller;

import com.acampoverde.ms_client_banking.infraestructure.in.dto.CustomerDto;
import com.acampoverde.ms_client_banking.infraestructure.in.handler.CustomerHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/customer")

public class CustomerController {
    private final CustomerHandler customerHandler;

    public CustomerController(CustomerHandler customerHandler) {
        this.customerHandler = customerHandler;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> finAllCustomer() {
        return new ResponseEntity<>(customerHandler.findAllCustomers(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable Integer id) {
        return new ResponseEntity<>(customerHandler.findCustomerById(id), OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto customer = customerHandler.saveCustomer(customerDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customer) {
        return new ResponseEntity<>( customerHandler.updateCustomer(customer),OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        customerHandler.deleteCustomer(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
