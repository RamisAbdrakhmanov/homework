INSERT INTO employees(firstname, lastname, salary)
VALUES ('Alex', 'Smile', 200);
INSERT INTO employees(firstname, lastname, salary)
VALUES ('Abba', 'Don', 400);

INSERT INTO projects(name)
VALUES ('project_1');
INSERT INTO projects(name)
VALUES ('project_2');

INSERT INTO departments(name)
VALUES ('department_1');
INSERT INTO departments(name)
VALUES ('department_2');

INSERT INTO department_employees(department_id, employee_id)
VALUES (1, 1);
INSERT INTO department_employees(department_id, employee_id)
VALUES (2, 2);

INSERT INTO projects_employees(project_id, employee_id)
VALUES (1, 1);
INSERT INTO projects_employees(project_id, employee_id)
VALUES (2, 2);
INSERT INTO projects_employees(project_id, employee_id)
VALUES (1, 2);
INSERT INTO projects_employees(project_id, employee_id)
VALUES (2, 1);