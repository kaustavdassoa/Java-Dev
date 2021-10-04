package com.example.spring.kafka.stream.service;

import com.example.spring.kafka.stream.bindings.PosListenerBinding;
import com.example.spring.kafka.stream.model.PosInvoice;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@EnableBinding(PosListenerBinding.class)
public class POSInvoiceStreamService {

    @StreamListener("posInvoice-input-channel")
    @SendTo("posInvoice-output-channel")
    public KStream<String, PosInvoice> maskPosInvoice(KStream<String, PosInvoice> inboundStream)
    {
        inboundStream.foreach((key, invoice) -> {
            log.info("Reading invoice # "+invoice.getInvoiceNumber());
        });

        KStream<String, PosInvoice> maskedInvoice=inboundStream
                .filter((key, invoice) -> invoice.getCustomerType().equalsIgnoreCase("PRIME"))
                .mapValues(invoice -> maskInvoice(invoice));
        return maskedInvoice;
    }


    private PosInvoice maskInvoice(PosInvoice invoice)
    {
        invoice.setCustomerCardNo("XXXXXX");
        invoice.getDeliveryAddress().setAddressLine("XXXX");
        invoice.getDeliveryAddress().setCity("XXXX");
        invoice.getDeliveryAddress().setState("XXXX");
        invoice.getDeliveryAddress().setContactNumber("XXXX");


        return invoice;
    }
}
