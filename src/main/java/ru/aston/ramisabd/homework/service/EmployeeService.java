package ru.aston.ramisabd.homework.service;

import ru.aston.ramisabd.homework.model.Employee;

import java.util.List;

public interface EmployeeService {

    void gen();

    List<Employee> getEmployeeBySalary(Integer salary);

    Employee getEmployee(Long id);

    List<Employee> getEmployees();

    Long getCount();

    void save(Employee t);

    void update(Employee t);

    void delete(Long id);
}
