package com.cyimana.invoicing.controllers;

import com.cyimana.invoicing.dtos.CustomerDto;
import com.cyimana.invoicing.dtos.InvoiceDto;
import com.cyimana.invoicing.entities.Invoice;
import com.cyimana.invoicing.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody InvoiceDto invoiceDto) {
        this.invoiceService.create(invoiceDto);
        return ResponseEntity.ok("Invoice created successfully");
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Invoice>> allInvoices() {
        Iterable<Invoice> invoices = this.invoiceService.allInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long id) {
        Invoice invoice = this.invoiceService.getInvoice(id);
        return ResponseEntity.ok(invoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(Long id, InvoiceDto invoiceDto) {
        Invoice updatedInvoice = this.invoiceService.updateInvoice(id, invoiceDto);
        return ResponseEntity.ok(updatedInvoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(Long id) {
        this.invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

}
