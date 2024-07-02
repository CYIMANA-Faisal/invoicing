package com.cyimana.invoicing.controllers;

import com.cyimana.invoicing.dtos.CustomerDto;
import com.cyimana.invoicing.entities.Customer;
import com.cyimana.invoicing.entities.User;
import com.cyimana.invoicing.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<Customer> create(@RequestBody @Valid CustomerDto customerDto) {
        Customer createdCustomer = this.customerService.create(customerDto);
        return ResponseEntity.ok(createdCustomer);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Customer>> allCustomers() {
        Iterable<Customer> customers = this.customerService.allCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = this.customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        Customer updatedCustomer = this.customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        this.customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
