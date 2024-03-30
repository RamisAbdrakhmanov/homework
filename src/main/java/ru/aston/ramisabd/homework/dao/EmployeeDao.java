package ru.aston.ramisabd.homework.dao;

import ru.aston.ramisabd.homework.model.Employee;

import java.util.List;


public interface EmployeeDao {
    List<Employee> findByName(String firstname, String lastname);

    List<Employee> findBySalary(Long salary);

    Employee findById(Long id);

    List<Employee> findAll();

    Long getCount();

    void save(Employee t);

    void update(Employee t);

    void delete(Long id);

}
