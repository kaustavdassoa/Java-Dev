package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LambdaWithParameters {

    public static void main(String[] args) {
        Employee jhon=new Employee("Jhon",12);
        Employee roberta=new Employee("roberta",32);
        Employee mike=new Employee("Mike",22);
        Employee alex=new Employee("Alex",12);

        ArrayList<Employee> team=new ArrayList<>();
        team.add(jhon);
        team.add(roberta);
        team.add(mike);
        team.add(alex);



        printTeam(team);


        //Without using Lambda
        Collections.sort(team, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getAge() > employee2.getAge() ? 1 : -1;
            }
        });
        printTeam(team);





        ArrayList<Employee> lambdaTeam=new ArrayList<>();

        lambdaTeam.add(jhon);
        lambdaTeam.add(roberta);
        lambdaTeam.add(mike);
        lambdaTeam.add(alex);

        Collections.sort(lambdaTeam, (Employee emp1,Employee emp2) -> emp1.getAge() > emp2.getAge() ? 1 : -1);
        printTeam(lambdaTeam);


    }

    private static void printTeam(ArrayList<Employee> team)
    {
        System.out.print("\n [");
        for(Employee emp: team)
        {
            System.out.print(emp.getName()+" ");
        }
        System.out.print("]");
    }

}


//Pojo
class Employee
{
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}