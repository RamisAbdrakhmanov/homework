package ru.aston.ramisabd.homework;

import ru.aston.ramisabd.homework.model.Employee;
import ru.aston.ramisabd.homework.service.EmployeeService;
import ru.aston.ramisabd.homework.service.EmployeeServiceImpl;

import java.util.ArrayList;

public class App {
    static EmployeeService employeeService = new EmployeeServiceImpl();

    public static void main(String[] args) {
        Employee employee = employeeService.getEmployee(1L);
        System.out.println(employee);
        System.out.println("__________________________________________________________");

        employee = new Employee(null, "AAAA", "BBBB", 3000, new ArrayList<>());
        employeeService.save(employee);
        System.out.println(employeeService.getEmployees());
        System.out.println("__________________________________________________________");

        employeeService.delete(employee.getId());
        System.out.println(employeeService.getEmployees());
        System.out.println("__________________________________________________________");


    }
}
