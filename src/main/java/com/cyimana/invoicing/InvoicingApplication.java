package com.cyimana.invoicing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class InvoicingApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoicingApplication.class, args);
	}

}
