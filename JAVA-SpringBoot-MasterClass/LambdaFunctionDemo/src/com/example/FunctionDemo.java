package com.example;

import java.util.ArrayList;
import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args) {

        ArrayList<Employee> employees= new ArrayList<>();
        employees.add(new Employee("Jhon Milo",12));
        employees.add(new Employee("Robarta Bela",23));
        employees.add(new Employee("Mike Nick",34));


        Function <Employee, String> getLastName= (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(" "),employee.getName().length()-1);
           };


        employees.forEach(employee -> {
            System.out.println(employee.getName()+" Last Name is"+getLastName.apply(employee));
        });

    }//main
}
