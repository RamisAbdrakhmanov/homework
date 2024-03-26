package ru.aston.ramisabd.homework.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    T findById(ID id);

    T findReferenceById(ID id);

    List<T> findAll();

    Long getCount();

    T makePersistent(T entity);

    void makeTransient(T entity);

    void checkVersion(T entity, boolean forceUpdate);
}
