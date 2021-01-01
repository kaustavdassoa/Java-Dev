package com.example;

public class LambdaDemo {

    public static void main(String[] args) {
        
        //Implementation 1
        new Thread(new MyRunable()).start();

        //Implementation 2
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printing from Runnable functional interface implementation.");            }
        }).start();


        //Implementation 3
        new Thread(()-> System.out.println("Printing from Lambda function.")).start();

    }
}


//
class MyRunable implements Runnable{
    @Override
    public void run() {
        System.out.println("Printing from MyRunable class implementation.");
    }
}