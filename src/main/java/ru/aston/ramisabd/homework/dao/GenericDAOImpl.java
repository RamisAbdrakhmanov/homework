package ru.aston.ramisabd.homework.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.criteria.CriteriaQuery;
import ru.aston.ramisabd.homework.utils.SessionUtil;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    protected SessionUtil sessionUtil = new SessionUtil();
    protected EntityManager em;

    protected final Class<T> entityClass;

    protected GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public T findById(ID id) {
        sessionUtil.openTransactionSession();
        T t = sessionUtil.getSession().get(entityClass, id);
        sessionUtil.closeTransactionSession();
        return t;
    }

    public T findReferenceById(ID id) {
        return em.getReference(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery<T> c =
                em.getCriteriaBuilder().createQuery(entityClass);
        c.select(c.from(entityClass));
        return em.createQuery(c).getResultList();
    }

    public Long getCount() {
        CriteriaQuery<Long> c =
                em.getCriteriaBuilder().createQuery(Long.class);
        c.select(em.getCriteriaBuilder().count(c.from(entityClass)));
        return em.createQuery(c).getSingleResult();
    }

    public T makePersistent(T instance) {
        // merge() handles transient AND detached instances
        return em.merge(instance);
    }

    public void makeTransient(T instance) {
        em.remove(instance);
    }

    public void checkVersion(T entity, boolean forceUpdate) {
        em.lock(
                entity,
                forceUpdate
                        ? LockModeType.OPTIMISTIC_FORCE_INCREMENT
                        : LockModeType.OPTIMISTIC
        );
    }
}
