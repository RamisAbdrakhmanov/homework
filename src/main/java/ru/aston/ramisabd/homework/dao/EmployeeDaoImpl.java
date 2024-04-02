package ru.aston.ramisabd.homework.dao;

import org.springframework.stereotype.Repository;
import ru.aston.ramisabd.homework.model.Employee;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Random;


@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;


    public void generateDataBaseForIndex() {
        Random rand = new Random();
        Employee employee = null;
        for (int i = 0; i < 1_000_000; i++) {
            employee = new Employee(null, "Data", "Check", rand.nextInt(10_000), null);
            em.persist(employee);
        }
    }


    @Override
    public Employee findById(Long id) {
        return em.createQuery(
                        "select e " +
                                "from Employee e " +
                                "where e.id = :id ", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
    }


    @Override
    public List<Employee> findAll() {
        EntityGraph<?> entityGraph = em.createEntityGraph("graph.employee");
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e ", Employee.class)
                .setHint("javax.persistence.fetchgraph", entityGraph);
        return q.getResultList();
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
    public List<Employee> findBySalary(Integer salary) {

        EntityGraph<?> entityGraph = em.createEntityGraph("graph.employee");
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e " +
                        "where salary > :salary", Employee.class)
                .setParameter("salary", salary)
                .setHint("javax.persistence.fetchgraph", entityGraph);

        return q.getResultList();
    }


    @Override
    public Long getCount() {
        Query q = em.createQuery("select count(Employee.id) from Employee ");
        return (Long) q.getSingleResult();
    }

    @Override
    public void save(Employee t) {

    }

    @Override
    public void update(Employee t) {

    }

    @Override
    public void delete(Long id) {
        em.createQuery("delete from Employee e where e.id= :id")
                .setParameter("id", id).executeUpdate();

    }
}
