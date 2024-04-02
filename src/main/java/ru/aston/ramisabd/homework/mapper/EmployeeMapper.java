package ru.aston.ramisabd.homework.mapper;

import lombok.experimental.UtilityClass;
import ru.aston.ramisabd.homework.model.Employee;
import ru.aston.ramisabd.homework.model.dto.EmployeeDto;

@UtilityClass
public class EmployeeMapper {

    public EmployeeDto toEmployeeDto(Employee employee){
        return  new EmployeeDto(employee.getId(), employee.getFirstname(), employee.getLastname());
    }
}
