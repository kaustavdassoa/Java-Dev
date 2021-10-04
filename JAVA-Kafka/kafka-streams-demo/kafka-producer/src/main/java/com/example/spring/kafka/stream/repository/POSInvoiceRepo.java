package com.example.spring.kafka.stream.repository;

import com.example.spring.kafka.stream.model.DeliveryAddress;
import com.example.spring.kafka.stream.model.LineItem;
import com.example.spring.kafka.stream.model.PosInvoice;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class POSInvoiceRepo {

    public PosInvoice getInvoice(String invoiceNumber)
    {
        PosInvoice posInvoice;
        posInvoice = PosInvoice.builder()
                .invoiceNumber(invoiceNumber)
                .createdTime(new Date().getTime())
                .storeID("STORE-01")
                .posID("POS-01")
                .cashierID("u12345")
                .customerType("PRIME")
                .customerCardNo("123456789")
                .totalAmount(0.0)
                .numberOfItems(0)
                .paymentMethod("CARD")
                .taxableAmount(0.0)
                .cGST(0.0)
                .sGST(0.0)
                .cESS(0.0)
                .deliveryType("TAKE-AWAY")
                .deliveryAddress(getDeliveryAddress(invoiceNumber))
                .invoiceLineItems(getInvoiceItems(invoiceNumber))
                .build();
        return posInvoice;
    }

    private DeliveryAddress getDeliveryAddress(String invoiceNumber)
    {
        return DeliveryAddress.builder()
                .addressLine("7418 Dolor St.")
                .city("Guwahati")
                .state("Assam")
                .pinCode("7829673917")
                .build();
    }
    private List<LineItem> getInvoiceItems(String invoiceNumber)
    {
        return Arrays.asList(LineItem.builder()
                .itemCode("208")
                .itemDescription("Test Item")
                .itemPrice(1234.00)
                .itemQty(1)
                .build());
    }
}
