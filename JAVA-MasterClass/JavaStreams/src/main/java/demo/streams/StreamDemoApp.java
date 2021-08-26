package demo.streams;

import demo.streams.domain.Employees;
import demo.streams.reposirtory.EmployeeRepository;
import demo.streams.domain.Department;

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


        List<String> it_department_employee_phone_numbers =  employeeList.stream()
                                                          .filter(employees -> employees.getDepartment() == Department.IT)
                                                          .flatMap(employees -> employees.getPhoneNumber().stream())
                                                          .sorted()
                                                          .collect(Collectors.toList());

        System.out.println("it_department_employee_phone_numbers = "+it_department_employee_phone_numbers);
    }
}
