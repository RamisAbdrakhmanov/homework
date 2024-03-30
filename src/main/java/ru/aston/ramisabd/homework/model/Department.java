package ru.aston.ramisabd.homework.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @OneToMany
    @JoinTable(name = "department_employees",
            joinColumns = {@JoinColumn(name = "department_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    private List<Employee> employees;
}
