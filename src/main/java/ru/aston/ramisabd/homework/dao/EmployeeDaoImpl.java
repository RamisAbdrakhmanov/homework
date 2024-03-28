package ru.aston.ramisabd.homework.dao;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aston.ramisabd.homework.model.Employee;
import ru.aston.ramisabd.homework.utils.HibernateUtil;

import javax.ejb.Stateless;
import java.util.List;

import static org.hibernate.resource.transaction.spi.TransactionStatus.ACTIVE;
import static org.hibernate.resource.transaction.spi.TransactionStatus.MARKED_ROLLBACK;

@Stateless
public class EmployeeDaoImpl extends GenericDAOImpl<Employee, Long> implements EmployeeDao {

    public EmployeeDaoImpl() {
        super(Employee.class);
    }


    @Override
    public Employee findById(Long id) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = null;
        try {
            EntityGraph<?> entityGraph = session.createEntityGraph("graph.employee");
            TypedQuery<Employee> q = session.createQuery("SELECT e FROM Employee e " +
                            "WHERE e.id = :id", Employee.class)
                    .setHint("javax.persistence.fetchgraph", entityGraph);

            q.setParameter("id", id);

            employee = q.getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        List<Employee> tList = null;
        try {
            EntityGraph<?> entityGraph = session.createEntityGraph("graph.employee");
            TypedQuery<Employee> q = session.createQuery("SELECT e FROM Employee e ", Employee.class)
                    .setHint("javax.persistence.fetchgraph", entityGraph);

            tList = q.getResultList();

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

    @Override
    public List<Employee> findByName(String firstname, String lastname) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        List<Employee> employees = null;
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
            criteria.select(criteria.from(Employee.class));

            Root<Employee> i = criteria.from(Employee.class);

            TypedQuery<Employee> query = session.createQuery(
                    criteria.where(
                            cb.equal(
                                    i.get("firstname"), firstname),
                            cb.equal(
                                    i.get("lastname"), lastname)));
            employees = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return employees;
    }

    @Override
    public List<Employee> findBySalary(Long salary) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        List<Employee> employees = null;
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
            criteria.select(criteria.from(Employee.class));

            Root<Employee> i = criteria.from(Employee.class);
            TypedQuery<Employee> query = session.createQuery(
                    criteria.where(
                            cb.gt(i.get("salary"), salary)));
            employees = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return employees;
    }
}
