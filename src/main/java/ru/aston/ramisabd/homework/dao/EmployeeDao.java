package ru.aston.ramisabd.homework.dao;

import ru.aston.ramisabd.homework.model.Employee;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public interface EmployeeDao {
    List<Employee> findByName(String firstname, String lastname);

    List<Employee> findBySalary(Long salary);

}
