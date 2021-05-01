package com.designpatterns.decorator.example1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FileWritter implements  Writer{

    @Override
    public void write(String text) throws IOException {
        File file=new File(UUID.randomUUID().toString()+".txt");
        new FileOutputStream(file).write(text.getBytes());
        System.out.println("Successfully encryted and writtern to "+file.getName()+" file");

    }
}
