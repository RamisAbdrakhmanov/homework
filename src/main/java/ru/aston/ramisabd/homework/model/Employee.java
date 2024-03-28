package ru.aston.ramisabd.homework.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
@NamedEntityGraph(
        name = "graph.employee",
        attributeNodes = @NamedAttributeNode("projects")
)
public class Employee extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Employee(Long id, String firstname, String lastname, Integer salary, List<Project> projects) {
        super.setFirstname(firstname);
        this.id = id;
        this.lastname = lastname;
        this.salary = salary;
        this.projects = projects;
    }
}
