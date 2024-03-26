package ru.aston.ramisabd.homework.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class User {

    @Column
    private String firstname;
}
