package com.cyimana.invoicing.services;

import com.cyimana.invoicing.configs.RabbitMQConfig;
import com.cyimana.invoicing.entities.Invoice;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void listener(String message) {
        System.out.println("Invoice from MQTT: " + message);
    }
}
