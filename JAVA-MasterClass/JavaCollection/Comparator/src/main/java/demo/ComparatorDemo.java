package com.example.comparable.demo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Employee implements Comparable<Employee>{

    private int empId;
    private String empName;
    private int departmentId;

    Employee(int empId,String empName,int departmentId)
    {
        this.empId=empId;
        this.empName=empName;
        this.departmentId=departmentId;
    }


    @Override
    public int compareTo(Employee e) {

        return this.departmentId - e.departmentId;
    }

    @Override
    public String toString() {
        return "{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", departmentId=" + departmentId +
                '}'+"\n";
    }
}


public class ComparableDemo {

    public static void main(String[] args) {

        List employeeList=new ArrayList();

        employeeList.add(new Employee(1,"Mark",202));
        employeeList.add(new Employee(2,"Tom",201));
        employeeList.add(new Employee(3,"Jerry",102));
        employeeList.add(new Employee(4,"Cat",202));


        Collections.sort(employeeList);
        System.out.println(employeeList);
    }

}
