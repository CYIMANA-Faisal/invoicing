package com.cyimana.invoicing.services;

import com.cyimana.invoicing.configs.RabbitMQConfig;
import com.cyimana.invoicing.dtos.InvoiceDto;
import com.cyimana.invoicing.entities.Customer;
import com.cyimana.invoicing.entities.Invoice;
import com.cyimana.invoicing.repositories.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private RabbitTemplate template;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private InvoiceService invoiceService;

    private InvoiceDto invoiceDto;
    private Invoice invoice;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");

        invoiceDto = new InvoiceDto();
        invoiceDto.setCustomerId(1L);
        invoiceDto.setAmount(100.0);

        invoice = new Invoice();
        invoice.setId(1L);
        invoice.setCustomer(customer);
        invoice.setAmount(100.0);
        invoice.setInvoiceDate(LocalDateTime.now());
    }

    @Test
    void testCreateInvoice() {
        when(customerService.getCustomer(anyLong())).thenReturn(customer);
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);

        invoiceService.create(invoiceDto);

        verify(customerService, times(1)).getCustomer(anyLong());
        verify(invoiceRepository, times(1)).save(any(Invoice.class));
        verify(template, times(1)).convertAndSend(anyString(), anyString(), anyString());
    }

    @Test
    void testAllInvoices() {
        when(invoiceRepository.findAll()).thenReturn(Arrays.asList(invoice));

        Iterable<Invoice> invoices = invoiceService.allInvoices();

        assertNotNull(invoices);
        assertTrue(invoices.iterator().hasNext());
        verify(invoiceRepository, times(1)).findAll();
    }

    @Test
    void testGetInvoice() {
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));

        Invoice foundInvoice = invoiceService.getInvoice(1L);

        assertNotNull(foundInvoice);
        assertEquals(1L, foundInvoice.getId());
        assertEquals(100.0, foundInvoice.getAmount());
        verify(invoiceRepository, times(1)).findById(anyLong());
    }

    @Test
    void testUpdateInvoice() {
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));
        when(customerService.getCustomer(anyLong())).thenReturn(customer);
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);

        Invoice updatedInvoice = invoiceService.updateInvoice(1L, invoiceDto);

        assertNotNull(updatedInvoice);
        assertEquals(1L, updatedInvoice.getId());
        assertEquals(100.0, updatedInvoice.getAmount());
        verify(invoiceRepository, times(1)).findById(anyLong());
        verify(customerService, times(1)).getCustomer(anyLong());
        verify(invoiceRepository, times(1)).save(any(Invoice.class));
    }

    @Test
    void testDeleteInvoice() {
        doNothing().when(invoiceRepository).deleteById(anyLong());

        invoiceService.deleteInvoice(1L);

        verify(invoiceRepository, times(1)).deleteById(anyLong());
    }
}
