package ru.aston.ramisabd.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.aston.ramisabd.homework.mapper.EmployeeMapper;
import ru.aston.ramisabd.homework.model.Employee;
import ru.aston.ramisabd.homework.model.dto.EmployeeDto;
import ru.aston.ramisabd.homework.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    @ResponseBody
    public EmployeeDto getEmployee(@PathVariable Long id) {
        return EmployeeMapper.toEmployeeDto(employeeService.getEmployee(id));
    }

    @GetMapping
    @ResponseBody
    public List<EmployeeDto> getEmployees() {
        return employeeService.getEmployees()
                .stream().map(EmployeeMapper::toEmployeeDto).collect(Collectors.toList());
    }

    @PostMapping
    public void save(@RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @PutMapping
    public void update(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @DeleteMapping
    public void delete(Long id){
        employeeService.delete(id);
    }
}
