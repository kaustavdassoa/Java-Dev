package com.designpatterns.decorator.example1;

import java.io.IOException;

public class DBWritter implements Writer{

    @Override
    public void write(String text) throws IOException {
        System.out.println("successfully stored in DB");
    }
}
