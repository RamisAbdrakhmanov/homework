package ru.aston.ramisabd.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Department {
    private Long id;
    private String name;
    private List<Employee> employees;
}
