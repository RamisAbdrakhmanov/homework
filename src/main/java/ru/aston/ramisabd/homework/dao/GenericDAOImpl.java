package ru.aston.ramisabd.homework.dao;

import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aston.ramisabd.homework.utils.HibernateUtil;

import java.util.List;

import static org.hibernate.resource.transaction.spi.TransactionStatus.ACTIVE;
import static org.hibernate.resource.transaction.spi.TransactionStatus.MARKED_ROLLBACK;

public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    protected final Class<T> entityClass;

    protected GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T findById(ID id) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        T t = null;
        try {
            t = session.get(entityClass, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return t;
    }

    public T findReferenceById(ID id) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        T t = null;
        try {
            t = session.getReference(entityClass, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return t;
    }

    public List<T> findAll() {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        List<T> tList = null;
        try {
            CriteriaQuery<T> c =
                    session.getCriteriaBuilder().createQuery(entityClass);
            c.select(c.from(entityClass));
            tList = session.createQuery(c).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return tList;
    }

    public Long getCount() {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        Long count = null;
        try {
            CriteriaQuery<Long> c =
                    session.getCriteriaBuilder().createQuery(Long.class);
            c.select(session.getCriteriaBuilder().count(c.from(entityClass)));
            count = session.createQuery(c).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return count;
    }

    @Override
    public void save(T t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.persist(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(T t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.merge(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            T t = session.get(entityClass, id);
            session.remove(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }
}
