package ru.aston.ramisabd.homework.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
@NamedEntityGraph(
        name = "graph.employee",
        attributeNodes = @NamedAttributeNode("projects")
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private Integer salary;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "projects_employees",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private List<Project> projects;

}
