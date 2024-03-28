package ru.aston.ramisabd.homework.service;

import jakarta.transaction.Transactional;
import ru.aston.ramisabd.homework.dao.EmployeeDaoImpl;
import ru.aston.ramisabd.homework.model.Employee;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local(EmployeeService.class)
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    @Override
    @Transactional
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
