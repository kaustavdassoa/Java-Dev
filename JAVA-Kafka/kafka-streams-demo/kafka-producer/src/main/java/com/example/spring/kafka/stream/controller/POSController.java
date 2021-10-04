package com.example.spring.kafka.stream.controller;

import com.example.spring.kafka.stream.model.PosInvoice;
import com.example.spring.kafka.stream.repository.POSInvoiceRepo;
import com.example.spring.kafka.stream.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class POSController {

    @Autowired
    POSInvoiceRepo repo;

    @Autowired
    KafkaProducerService kafkaProducerService;

    @PostMapping("/sendinvoice")
    public ResponseEntity<PosInvoice> addBook()
    {

        UUID uuid = UUID.randomUUID();
        String invoiceNumber = uuid.toString();

        PosInvoice invoice=repo.getInvoice(invoiceNumber);
        kafkaProducerService.sendMessage(invoice);

        return ResponseEntity.status(200).body(invoice);
    }
}
