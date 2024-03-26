package ru.aston.ramisabd.homework.utils;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.aston.ramisabd.homework.model.Department;
import ru.aston.ramisabd.homework.model.Employee;
import ru.aston.ramisabd.homework.model.Project;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Project.class);
                configuration.addAnnotatedClass(Department.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

    @PersistenceContext
    public static EntityManager getSession() {
        return getSessionFactory().openSession();
    }
}
