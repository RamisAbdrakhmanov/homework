package ru.aston.ramisabd.homework.utils;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Getter
public class SessionUtil {
    private Session session;
    private Transaction transaction;

    public Session openSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    public Session openTransactionSession() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSession() {
        session.close();
    }

    public void closeTransactionSession() {
        transaction.commit();
        closeSession();
    }
}
