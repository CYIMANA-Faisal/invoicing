package com.cyimana.invoicing.services;

import com.cyimana.invoicing.dtos.CustomerDto;
import com.cyimana.invoicing.entities.Customer;
import com.cyimana.invoicing.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private CustomerDto customerDto;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customerDto = new CustomerDto();
        customerDto.setName("John Doe");
        customerDto.setEmail("john.doe@example.com");
        customerDto.setPhoneNumber("1234567890");

        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");
    }

    @Test
    void testCreateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer createdCustomer = customerService.create(customerDto);

        assertNotNull(createdCustomer);
        assertEquals("John Doe", createdCustomer.getName());
        assertEquals("john.doe@example.com", createdCustomer.getEmail());
        assertEquals("1234567890", createdCustomer.getPhoneNumber());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testAllCustomers() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));

        Iterable<Customer> customers = customerService.allCustomers();

        assertNotNull(customers);
        assertTrue(customers.iterator().hasNext());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetCustomer() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.getCustomer(1L);

        assertNotNull(foundCustomer);
        assertEquals("John Doe", foundCustomer.getName());
        assertEquals("john.doe@example.com", foundCustomer.getEmail());
        assertEquals("1234567890", foundCustomer.getPhoneNumber());
        verify(customerRepository, times(1)).findById(anyLong());
    }

    @Test
    void testUpdateCustomer() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer updatedCustomer = customerService.updateCustomer(1L, customerDto);

        assertNotNull(updatedCustomer);
        assertEquals("John Doe", updatedCustomer.getName());
        assertEquals("john.doe@example.com", updatedCustomer.getEmail());
        assertEquals("1234567890", updatedCustomer.getPhoneNumber());
        verify(customerRepository, times(1)).findById(anyLong());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(customerRepository).deleteById(anyLong());

        customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}
