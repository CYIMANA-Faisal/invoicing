package com.cyimana.invoicing.services;

import com.cyimana.invoicing.dtos.CustomerDto;
import com.cyimana.invoicing.entities.Customer;
import com.cyimana.invoicing.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        return this.customerRepository.save(customer);
    }

    public Iterable<Customer> allCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer getCustomer(Long id) {
        return this.customerRepository.findById(id).orElseThrow();
    }

    public Customer updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = this.customerRepository.findById(id).orElseThrow();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        return this.customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        this.customerRepository.deleteById(id);
    }

}
