package ru.aston.ramisabd.homework.crud;


import ru.aston.ramisabd.homework.utils.DBUtils;
import ru.aston.ramisabd.homework.model.Department;
import ru.aston.ramisabd.homework.model.Employee;
import ru.aston.ramisabd.homework.model.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudUser {
    private static String SELECT_EMPLOYEES = "SELECT * FROM employees;";
    private static String INSERT_EMPLOYEE = "INSERT INTO employees(firstname,lastname) VALUES(?,?);";
    private static String UPDATE_EMPLOYEE = "UPDATE employees SET firstname = ? WHERE id = ?;";
    private static String DELETE_STUDENT = "DELETE FROM employees WHERE id = ?;";
    private static String SELECT_DEPARTMENT_WITH_EMPLOYEES =
            "SELECT de.department_id, d.name, e.id, e.firstname, e.lastname " +
                    "FROM department_employees AS de " +
                    "LEFT JOIN departments AS d " +
                    "ON de.department_id = d.id  " +
                    "LEFT JOIN employees AS e " +
                    "ON de.employee_id = e.id " +
                    "ORDER BY de.department_id;";
    private static String SELECT_EMPLOYEES_WITH_PROJECTS =
            "SELECT pe.employee_id,  e.firstname, e.lastname, pe.project_id, p.name " +
                    "FROM projects_employees AS pe " +
                    "LEFT JOIN employees AS e " +
                    "ON pe.employee_id = e.id " +
                    "LEFT JOIN projects AS p " +
                    "ON pe.project_id = p.id " +
                    "ORDER BY pe.employee_id;";

    private static String SELECT_DEPARTMENTS = "SELECT * FROM departments;";

    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEES);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");

                employees.add(new Employee(id, firstname, lastname));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }

    public static List<Employee> saveEmployee(Employee employee) {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE);

            preparedStatement.setString(1, employee.getFirstname());
            preparedStatement.setString(2, employee.getLastname());
            preparedStatement.executeUpdate();

            PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEES);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");

                employees.add(new Employee(id, firstname, lastname));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }


    public static List<Employee> updateEmployee(long idUpd, String firstnameUpd) {
        List<Employee> employees = new ArrayList<>();


        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE);
            preparedStatement.setString(1, firstnameUpd);
            preparedStatement.setLong(2, idUpd);
            preparedStatement.executeUpdate();

            PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEES);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");

                employees.add(new Employee(id, firstname, lastname));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }

    public static List<Employee> deleteEmployee(long idDel) {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT);
            preparedStatement.setLong(1, idDel);
            preparedStatement.executeUpdate();

            PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEES);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");

                employees.add(new Employee(id, firstname, lastname));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }

    public static List<Department> getDepartmentWithEmployees() {
        List<Department> departments = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT_WITH_EMPLOYEES);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                long idDep = resultSet.getLong("department_id");
                String name = resultSet.getString("name");

                long id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");

                if (departments.isEmpty() || departments.get(departments.size() - 1).getId() != idDep) {
                    List<Employee> employees = new ArrayList<>();

                    employees.add(new Employee(id, firstname, lastname));

                    departments.add(new Department(idDep, name, employees));
                } else {
                    departments.get(departments.size() - 1).getEmployees().add(new Employee(id, firstname, lastname));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return departments;
    }

    public static List<Employee> getEmployeesWithProjects() {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEES_WITH_PROJECTS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long id = resultSet.getLong("employee_id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                long idPr = resultSet.getLong("project_id");
                String name = resultSet.getString("name");

                if (employees.isEmpty() || employees.get(employees.size() - 1).getId() != id) {
                    List<Project> projects = new ArrayList<>();

                    projects.add(new Project(idPr, name));

                    employees.add(new Employee(id, firstname, lastname, projects));
                } else {
                    employees.get(employees.size() - 1).getProjects().add(new Project(idPr, name));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }

    public static List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENTS);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                long idDep = resultSet.getLong("id");
                String name = resultSet.getString("name");

                departments.add(new Department(idDep, name, new ArrayList<>()));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return departments;
    }


}
