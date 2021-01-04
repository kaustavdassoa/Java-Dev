package com.example;

import java.util.ArrayList;
import java.util.function.Function;

public class ChainFunctionDemo {

    public static void main(String[] args) {
        ArrayList<Employee> employees= new ArrayList<>();
        employees.add(new Employee("Jhon Milo",12));
        employees.add(new Employee("Robarta Bela",23));
        employees.add(new Employee("Mike Nick",34));

        Function <String,String> toUpper = s -> s.toUpperCase();
        Function <Employee, String> getFirtName = employee -> employee.getName().substring(0,employee.getName().indexOf(" "));
        Function chainFunction = getFirtName.andThen(toUpper);


        employees.forEach(employee -> {
            System.out.println(chainFunction.apply(employee));
        });

    }
}
