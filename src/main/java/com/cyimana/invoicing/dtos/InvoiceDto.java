package com.cyimana.invoicing.dtos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Getter @Setter @ToString
public class InvoiceDto {
    private Long customerId;
    private double amount;
//    private Optional<String> invoiceDate;
}
