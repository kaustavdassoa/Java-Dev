package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LambdaForLoop {

    public static void main(String[] args) {

      //  Employee employeeInst;


        ArrayList <Employee> employees= new ArrayList<>();
        employees.add(new Employee("jhon",12));
        employees.add(new Employee("ken",37));
        employees.add(new Employee("joe",16));
        employees.add(new Employee("robarta",42));

        // new modified forloop with Lambda functions
        employees.forEach(employee -> System.out.println("Name :"+employee.getName()));




        //Predicate with Lambda Function
        printEmployeeByAgeConditions(employees, "Employees over 30",employeeInst -> employeeInst.getAge() > 10);

        //Predicate without Lambda Function
        printEmployeeByAgeConditions(employees, "\nEmployees younger than 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 25;
            }
        });



        Predicate<Employee> above20= new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 20;
            }
        };

        Predicate<Employee> below40= new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() <40;
            }
        };

        //Predicate with complex Predicate chain
        printEmployeeByAgeConditions(employees, "Employees above 20 and Below 30",above20.and(below40));


    }

    //Funciton using Pedicates
    public static void printEmployeeByAgeConditions(List<Employee> employeeList, String printMessage, Predicate<Employee> conditions)
    {
        System.out.println(printMessage);
        System.out.println("=====================");
        for(Employee employee:employeeList)
        {
            if(conditions.test(employee))
                System.out.println(employee.getName());
        }
    }
}

