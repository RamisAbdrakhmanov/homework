package ru.aston.ramisabd.homework;

import ru.aston.ramisabd.homework.model.Employee;
import ru.aston.ramisabd.homework.model.User;
import ru.aston.ramisabd.homework.service.EmployeeService;
import ru.aston.ramisabd.homework.service.EmployeeServiceImpl;

public class App {
    static EmployeeService employeeService = new EmployeeServiceImpl();

    public static void main(String[] args) {
        Employee employee = employeeService.getEmployee(1L);
        System.out.println(employee);
        System.out.println("__________________________________________________________");
        User user = employeeService.getEmployee(1L);
        System.out.println(user);
    }
}
