package ru.aston.ramisabd.homework;

import ru.aston.ramisabd.homework.crud.CrudUser;
import ru.aston.ramisabd.homework.model.Department;
import ru.aston.ramisabd.homework.model.Employee;

import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Employee> employees = CrudUser.getEmployees();
        System.out.println(employees);

        List<Employee> save = CrudUser.saveEmployee(new Employee("Jack","Daniels"));
        System.out.println(save);

        List<Employee> update = CrudUser.updateEmployee(1,"Idiot");
        System.out.println(update);

        List<Employee> delete = CrudUser.deleteEmployee(2);
        System.out.println(delete);

        List<Department> departmentsNull = CrudUser.getDepartments();
        System.out.println(departmentsNull);

        List<Department> departments = CrudUser.getDepartmentWithEmployees();
        System.out.println(departments);

        List<Employee> employeesWithProjects = CrudUser.getEmployeesWithProjects();
        System.out.println(employeesWithProjects);

    }
}
