create database week1;
use week1;
CREATE TABLE Departments (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL
);

CREATE TABLE Employees (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    dept_id INT,
    salary DECIMAL(12,2),
    manager_id INT,
    join_date DATE,

    FOREIGN KEY (dept_id)
        REFERENCES Departments(dept_id),

    FOREIGN KEY (manager_id)
        REFERENCES Employees(emp_id)
);

CREATE TABLE Projects (
    project_id INT PRIMARY KEY,
    project_name VARCHAR(100) NOT NULL,
    dept_id INT,
    budget DECIMAL(15,2),

    FOREIGN KEY (dept_id)
        REFERENCES Departments(dept_id)
);


CREATE TABLE EmployeeProjects (
    emp_id INT,
    project_id INT,
    hours_worked INT,

    PRIMARY KEY (emp_id, project_id),

    FOREIGN KEY (emp_id)
        REFERENCES Employees(emp_id),

    FOREIGN KEY (project_id)
        REFERENCES Projects(project_id)
);

CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    customer_id INT NOT NULL,
    order_date DATE NOT NULL,
    amount DECIMAL(12,2),
    status VARCHAR(20)
);


INSERT INTO Departments (dept_id, dept_name) VALUES
(1, 'Engineering'),
(2, 'HR'),
(3, 'Finance'),
(4, 'Sales'),
(5, 'Marketing'),
(6, 'Legal');

INSERT INTO Employees
(emp_id, emp_name, dept_id, salary, manager_id, join_date)
VALUES
(101, 'Alice', 1, 120000, NULL, '2020-01-15'),
(105, 'Eva',   2,  80000, NULL, '2019-07-12'),
(108, 'Henry', 3, 110000, NULL, '2018-04-05'),
(111, 'Kate',  4, 100000, NULL, '2019-09-01'),
(115, 'Oscar', 5,  65000, NULL, '2020-10-10'),
(117, 'Quinn', 6,  90000, NULL, '2024-01-10');

INSERT INTO Employees
(emp_id, emp_name, dept_id, salary, manager_id, join_date)
VALUES
(102, 'Bob',     1, 90000, 101, '2021-03-10'),
(103, 'Charlie', 1, 90000, 101, '2022-06-20'),
(104, 'David',   1, 70000, 101, '2023-02-01'),

(106, 'Frank',   2, 60000, 105, '2021-01-10'),
(107, 'Grace',   2, 60000, 105, '2022-08-15'),

(109, 'Irene',   3, 75000, 108, '2020-11-20'),
(110, 'Jack',    3, 50000, 108, '2023-01-15'),

(112, 'Leo',     4, 70000, 111, '2021-05-11'),
(113, 'Mia',     4, 70000, 111, '2022-03-18'),
(114, 'Nina',    4, 50000, 111, '2023-07-22'),

(116, 'Paul',    5, 45000, 115, '2022-02-14');

INSERT INTO Projects
(project_id, project_name, dept_id, budget)
VALUES
(201, 'AI Platform',        1, 500000),
(202, 'Mobile App',         1, 300000),
(203, 'Recruitment Drive',  2, 100000),
(204, 'Audit System',       3, 250000),
(205, 'Sales CRM',          4, 400000),
(206, 'Marketing Campaign', 5, 150000);

INSERT INTO EmployeeProjects
(emp_id, project_id, hours_worked)
VALUES

-- AI Platform
(101, 201, 120),
(102, 201, 200),
(103, 201, 200),
(104, 201, 150),

-- Mobile App
(101, 202, 100),
(102, 202, 180),
(104, 202, 180),

-- Recruitment Drive
(105, 203, 100),
(106, 203, 150),
(107, 203, 150),

-- Audit System
(108, 204, 200),
(109, 204, 120),
(110, 204, 120),

-- Sales CRM
(111, 205, 250),
(112, 205, 200),
(113, 205, 200),
(114, 205, 100),

-- Marketing Campaign
(115, 206, 180),
(116, 206, 150);

INSERT INTO Orders
(order_id, customer_id, order_date, amount, status)
VALUES

-- Customer 1
(1001, 1, '2025-01-10', 40000, 'COMPLETED'),
(1002, 1, '2025-02-15', 35000, 'COMPLETED'),
(1003, 1, '2025-03-20', 30000, 'COMPLETED'),

-- Customer 2
(1004, 2, '2025-01-05', 20000, 'COMPLETED'),
(1005, 2, '2025-02-12', 18000, 'COMPLETED'),
(1006, 2, '2025-03-25', 15000, 'COMPLETED'),

-- Customer 3
(1007, 3, '2025-01-15', 10000, 'COMPLETED'),
(1008, 3, '2025-02-18', 12000, 'COMPLETED'),

-- Customer 4
(1009, 4, '2025-01-20', 5000, 'COMPLETED'),
(1010, 4, '2025-04-20', 4000, 'COMPLETED'),

-- Customer 5 - consecutive months
(1011, 5, '2025-05-10', 10000, 'COMPLETED'),
(1012, 5, '2025-06-10', 12000, 'COMPLETED'),
(1013, 5, '2025-07-10', 15000, 'COMPLETED'),

-- Customer 6 - non-consecutive
(1014, 6, '2025-01-10', 20000, 'COMPLETED'),
(1015, 6, '2025-03-10', 20000, 'COMPLETED'),
(1016, 6, '2025-05-10', 20000, 'COMPLETED'),

-- Cancelled orders
(1017, 1, '2025-04-10', 50000, 'CANCELLED'),
(1018, 2, '2025-04-15', 30000, 'CANCELLED'),

-- Same date
(1019, 3, '2025-06-01', 15000, 'COMPLETED'),
(1020, 4, '2025-06-01', 10000, 'COMPLETED'),

(1021, 5, '2025-06-02', 20000, 'COMPLETED');

SELECT * FROM Departments;

SELECT * FROM Employees;

SELECT * FROM Projects;

SELECT * FROM EmployeeProjects;

SELECT * FROM Orders;


-- 1. Show department name, employee count, average, minimum and maximum salary. Include departments with no employees.
select d.dept_name , count(e.emp_id) as emp_count, avg(e.salary) as avg_salary, max(e.salary) as max_salary, min(e.salary) as min_salary from departments d left join employees e on d.dept_id = e.dept_id group by d.dept_id, d.dept_name;

-- 2. Using a window function, return employee(s) earning the second distinct highest salary in each department.
select dept_name, emp_name, salary from (select d.dept_name, e.emp_name, e.salary, dense_rank() over(partition by e.dept_id order by e.salary desc) as rank1 from employees e join departments d on e.dept_id = d.dept_id) x where rank1 = 2;

-- 3. Find employees earning more than their own department average without hard-coding departments.
select e.emp_id, e.emp_name, d.dept_name, e.salary from employees e join departments d on e.dept_id = d.dept_id where e.salary > (select avg(e2.salary) from employees e2 where e2.dept_id = e.dept_id);

-- 4. For each manager, show direct-report count, total team salary and manager salary; keep managers with at least two reports.
select m.emp_id as manager_id, m.emp_name as manager_name, m.salary as manager_salary, count(e.emp_id) as direct_report_count, sum(e.salary) as total_team_salary from employees m join employees e on e.manager_id = m.emp_id group by m.emp_id, m.emp_name, m.salary having count(e.emp_id) >= 2;

-- 5. For each project, rank employees by total hours and return the top two contributors, handling ties correctly.
select project_id, emp_id, total_hours from (select project_id, emp_id, sum(hours_worked) as total_hours, dense_rank() over(partition by project_id order by sum(hours_worked) desc) as rank1 from employeeprojects group by project_id, emp_id) x where rank1 <= 2;

-- 6. Find departments that contain employees but have no matching project.
select distinct d.dept_id,d.dept_name from departments d join employees e on d.dept_id=e.dept_id left join projects p on d.dept_id=p.dept_id where p.project_id is null;

-- 7. For completed orders, show order date, daily revenue and cumulative revenue ordered by date.
select order_date,daily_revenue,sum(daily_revenue) over(order by order_date) as cumulative_revenue from (select order_date,sum(amount) as daily_revenue from orders where status='completed' group by order_date) x order by order_date;

-- 8. Classify each customer by completed-order value: PLATINUM >=100000, GOLD >=50000, SILVER >=20000, else BRONZE.
select customer_id,sum(amount) as total_amount,case when sum(amount)>=100000 then 'platinum' when sum(amount)>=50000 then 'gold' when sum(amount)>=20000 then 'silver' else 'bronze' end as customer_class from orders where status='completed' group by customer_id;

-- 9. Identify customers with at least one order in three consecutive calendar months using CTEs/window functions.
select distinct o1.customer_id from orders o1 join orders o2 on o1.customer_id=o2.customer_id and year(o2.order_date)=year(date_add(o1.order_date,interval 1 month)) and month(o2.order_date)=month(date_add(o1.order_date,interval 1 month)) join orders o3 on o1.customer_id=o3.customer_id and year(o3.order_date)=year(date_add(o1.order_date,interval 2 month)) and month(o3.order_date)=month(date_add(o1.order_date,interval 2 month)) where o1.status='completed' and o2.status='completed' and o3.status='completed';

-- 10. For each department, return highest-paid and lowest-paid employee(s) and the salary difference; preserve ties.
select d.dept_name,e.emp_name,e.salary,case when e.salary=(select max(e2.salary) from employees e2 where e2.dept_id=e.dept_id) then 'highest' when e.salary=(select min(e2.salary) from employees e2 where e2.dept_id=e.dept_id) then 'lowest' end as salary_type,(select max(e2.salary) from employees e2 where e2.dept_id=e.dept_id)-(select min(e2.salary) from employees e2 where e2.dept_id=e.dept_id) as salary_difference from employees e join departments d on e.dept_id=d.dept_id where e.salary=(select max(e2.salary) from employees e2 where e2.dept_id=e.dept_id) or e.salary=(select min(e2.salary) from employees e2 where e2.dept_id=e.dept_id);