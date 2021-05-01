package com.designpatterns.decorator.example1;

public class Application {

    public static void main(String[] args) throws Exception{
        Writer fileWriter=new FileWritter();
        Writer dbWriter=new DBWritter();
        Writer ecryptedFileWriter=new EncryptedWritter(new FileWritter());
        Writer ecryptedDBWriter=new EncryptedWritter(new DBWritter());
        fileWriter.write("Sample text");
        ecryptedFileWriter.write("Sample text");
        dbWriter.write("Sample text");
        ecryptedDBWriter.write("Sample text");
    }
}
