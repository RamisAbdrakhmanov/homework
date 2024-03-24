package ru.aston.ramisabd.homework.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {
    @NonNull
    private Long id;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    private List<Project> projects;

    public Employee(@NonNull String firstname, @NonNull String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
