package ru.aston.ramisabd.homework.dao;

import ru.aston.ramisabd.homework.model.Department;

public class DepartmentDaoImpl extends GenericDAOImpl<Department, Long> {
    public DepartmentDaoImpl() {
        super(Department.class);
    }
}
