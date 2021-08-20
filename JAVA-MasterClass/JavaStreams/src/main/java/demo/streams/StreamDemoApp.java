package java.demo.streams.demo;

import java.demo.streams.demo.domain.Department;
import java.demo.streams.demo.domain.Employees;
import java.demo.streams.demo.reposirtory.EmployeeRepository;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemoApp {

    public static void main(String[] args) {
        EmployeeRepository repo=new EmployeeRepository();
        List<Employees> employeeList = repo.getAllEmpployee();

        List<String> it_department_employee_names = employeeList.stream()
                .filter(employees -> employees.getDepartment() == Department.IT)
                .map(employees -> employees.getName())
                .collect(Collectors.toList());

        System.out.println("it_department_employee_names ="+it_department_employee_names);

    }
}
