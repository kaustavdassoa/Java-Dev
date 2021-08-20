package java.demo.streams.demo.reposirtory;

import java.demo.streams.demo.domain.Department;
import java.demo.streams.demo.domain.Employees;
import java.util.List;

public class EmployeeRepository {

    private List<Employees> employeeList;

    public EmployeeRepository() {
        employeeList= List.of(new Employees("Employee 1", Department.IT,"employee1@test.com",List.of("111-111-1111","121-121-1211")),
                new Employees("Employee 2", Department.HR,"employee1@test.com",List.of("222-222-2222","221-221-2211")),
                new Employees("Employee 3", Department.IT,"employee3@test.com",List.of("333-333-3333","321-321-3211")));

    }

    public List<Employees> getAllEmpployee()
    {
        return employeeList;
    }
}
