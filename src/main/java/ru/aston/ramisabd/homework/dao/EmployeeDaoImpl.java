package ru.aston.ramisabd.homework.dao;

import org.springframework.stereotype.Repository;
import ru.aston.ramisabd.homework.model.Employee;
import ru.aston.ramisabd.homework.model.dto.EmployeeDto;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Employee findById(Long id) {
        System.out.println("___________________________________________________" +
                "________________________________________________________________" +
                "___________________________________________________________________" +
                "_____________________________________________________________________");

      /* EmployeeDto employeeDto = em.createQuery(
               "select new ru.aston.ramisabd.homework.model.dto.EmployeeDto(e.id, e.firstname, e.lastname) " +
               "from Employee e " +
                       "where e.id = :id ", EmployeeDto.class)
               .setParameter("id", id)
               .getSingleResult();*/

        Employee employee = em.createQuery(
                        "select e " +
                                "from Employee e " +
                                "where e.id = :id ", Employee.class)
                .setParameter("id", id)
                .getSingleResult();

        EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getFirstname(), employee.getLastname());

        return employee;
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

    }
}
