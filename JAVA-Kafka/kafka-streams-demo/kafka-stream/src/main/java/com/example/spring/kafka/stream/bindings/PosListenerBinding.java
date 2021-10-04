package com.example.spring.kafka.stream.bindings;


import com.example.spring.kafka.stream.model.PosInvoice;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
public interface PosListenerBinding {

    @Input("posInvoice-input-channel")
    KStream<String, PosInvoice> posInvoiceInputStream();

    @Output("posInvoice-output-channel")
    KStream<String, PosInvoice> posInvoiceOutputStream();
}
