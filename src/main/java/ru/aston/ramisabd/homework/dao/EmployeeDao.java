package ru.aston.ramisabd.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.ramisabd.homework.model.Employee;

import java.util.List;


public interface EmployeeDao extends JpaRepository<Employee, Long> {

    List<Employee> findBySalary(Integer salary);


}
