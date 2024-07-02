package com.cyimana.invoicing.services;

import com.cyimana.invoicing.configs.RabbitMQConfig;
import com.cyimana.invoicing.dtos.InvoiceDto;
import com.cyimana.invoicing.entities.Invoice;
import com.cyimana.invoicing.repositories.InvoiceRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final RabbitTemplate template;
    private final CustomerService customerService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, RabbitTemplate template, CustomerService customerService) {
        this.invoiceRepository = invoiceRepository;
        this.template = template;
        this.customerService = customerService;
    }

    public void create(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setCustomer(this.customerService.getCustomer(invoiceDto.getCustomerId()));
        invoice.setAmount(invoiceDto.getAmount());
        invoice.setInvoiceDate(LocalDateTime.now());
        Invoice crateredInvoice = this.invoiceRepository.save(invoice);
        template.convertAndSend(RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY, crateredInvoice.toString());
    }

    public Iterable<Invoice> allInvoices() {
        return this.invoiceRepository.findAll();
    }

    public Invoice getInvoice(Long id) {
        return this.invoiceRepository.findById(id).orElseThrow();
    }

    public Invoice updateInvoice(Long id, InvoiceDto invoiceDto) {
        Invoice invoice = this.invoiceRepository.findById(id).orElseThrow();
        invoice.setCustomer(this.customerService.getCustomer(invoiceDto.getCustomerId()));
        invoice.setAmount(invoiceDto.getAmount());
        return this.invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        this.invoiceRepository.deleteById(id);
    }

}
