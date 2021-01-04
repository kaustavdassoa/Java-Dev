package com.example;


public class LambdaCodeBlockDemo {

    public static void main(String[] args) {

        String string1="Hello";
        String string2="world";



        StringFunction stringFunction= (String s1,String s2) -> {
            System.out.println("Inside stringFunction implementaion");
            System.out.println("Value of "+s1.compareTo(s2));

            //block of code can be added here
            // All variable used inside the Lambda block should be final, non
            // variable define inside the lambda cann't be use outside lambda block
            return (s1.compareTo(s2) > 0) ?  s2.toUpperCase()+" "+s1.toUpperCase() : s1.toUpperCase()+" "+s2.toUpperCase();
        };

        System.out.println(stringFunction.compareAndContact("Hello","World"));
        System.out.println(stringFunction.compareAndContact("World","Hello"));

    }
}

interface StringFunction{

    public String compareAndContact(String string1, String string2);
}
