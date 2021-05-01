package com.designpatterns.decorator.example1;

import java.io.IOException;
import java.util.Base64;

public class EncryptedWritter implements Writer{

    Writer writer;
    public EncryptedWritter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void write(String text) throws IOException {
        String encodedText = Base64.getEncoder().encodeToString(text.getBytes());

        writer.write(encodedText);
    }
}
