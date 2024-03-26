package ru.aston.ramisabd.homework.dao;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ru.aston.ramisabd.homework.model.Employee;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class EmployeeDaoImpl extends GenericDAOImpl<Employee, Long> implements EmployeeDao {

    public EmployeeDaoImpl() {
        super(Employee.class);
    }

    @Override
    public Employee findById(Long id) {
        sessionUtil.openTransactionSession();

        EntityGraph<?> entityGraph = sessionUtil.getSession().createEntityGraph("graph.employee");
        TypedQuery<Employee> q = sessionUtil.getSession()
                .createQuery("SELECT e FROM Employee e " +
                        "WHERE e.id = :id", Employee.class)
                .setHint("javax.persistence.fetchgraph", entityGraph);

        q.setParameter("id", id);

        Employee employee = q.getSingleResult();
        sessionUtil.closeTransactionSession();
        return employee;
    }

    @Override
    public List<Employee> findByName(String firstname, String lastname) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        criteria.select(criteria.from(Employee.class));

        Root<Employee> i = criteria.from(Employee.class);

        TypedQuery<Employee> query = em.createQuery(
                criteria.where(
                        cb.equal(
                                i.get("firstname"), firstname),
                        cb.equal(
                                i.get("lastname"), lastname)));

        return query.getResultList();
    }

    @Override
    public List<Employee> findBySalary(Long salary) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        criteria.select(criteria.from(Employee.class));

        Root<Employee> i = criteria.from(Employee.class);
        TypedQuery<Employee> query = em.createQuery(
                criteria.where(
                        cb.gt(i.get("salary"), salary)));

        return query.getResultList();
    }
}
