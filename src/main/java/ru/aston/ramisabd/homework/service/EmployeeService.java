package ru.aston.ramisabd.homework.service;

import jakarta.persistence.LockModeType;
import ru.aston.ramisabd.homework.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployee(Long id);

    List<Employee> getEmployees();

    Long getCount();

    void saveEmployee(Employee employee);

   /* T findById(ID id, LockModeType lock);

    Employee findReferenceById(ID id);

    T makePersistent(T entity);

    void makeTransient(T entity);

    void checkVersion(T entity, boolean forceUpdate);*/
}
