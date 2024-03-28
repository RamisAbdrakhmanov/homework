package ru.aston.ramisabd.homework.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    T findById(ID id);

    T findReferenceById(ID id);

    List<T> findAll();

    Long getCount();

    void save(T t);

    void update(T t);

    void delete(Long id);

}
