package ru.aston.ramisabd.homework.service;

import jakarta.transaction.Transactional;
import ru.aston.ramisabd.homework.dao.EmployeeDao;
import ru.aston.ramisabd.homework.dao.EmployeeDaoImpl;
import ru.aston.ramisabd.homework.model.Employee;
import ru.aston.ramisabd.homework.utils.SessionUtil;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Local(EmployeeService.class)
//@Remote(RemoteAuctionService.class)
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
    public void saveEmployee(Employee employee) {
        employeeDao.makePersistent(employee);
    }
}
