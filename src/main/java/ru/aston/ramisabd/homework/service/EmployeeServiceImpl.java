package ru.aston.ramisabd.homework.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.ramisabd.homework.dao.EmployeeDao;
import ru.aston.ramisabd.homework.model.Employee;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    EmployeeDao employeeDao;

    @Override
    public Employee getEmployee(Long id) {
        return employeeDao.findById(id);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public Long getCount() {
        return employeeDao.getCount();
    }

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }


}
