use soft_uni;
-- 1
SELECT 
    *
FROM
    departments
ORDER BY department_id;

-- 2
SELECT 
    name
FROM
    departments
ORDER BY department_id;

-- 3
 SELECT 
    first_name, last_name, salary
FROM
    employees
ORDER BY employee_id;
 
 -- 4
SELECT 
    first_name, middle_name, last_name
FROM
    employees
ORDER BY employee_id;
 
 -- 5
 
 select concat (first_name,'.',last_name,'@softuni.bg' )
 from employees;
 
 -- 6
select distinct (salary)
from employees;

-- 7 
SELECT 
employee_id AS 'ID',
    first_name AS 'First Name',
    last_name AS 'Last Name',
     middle_name AS 'Middle Name',
    job_title AS 'Job Title',
    department_id AS 'Dept ID',
    manager_id AS 'Mngr ID',
    hire_date AS 'Hire Date',
    salary,
    address_id AS 'address'
FROM
    employees
WHERE
    job_title = 'Sales Representative'
order by ID ; 

-- 8 
SELECT 
    first_name, last_name, job_title
FROM
    employees
WHERE
    salary >= 20000 AND salary <= 30000;
    
-- 9

SELECT 
    concat_ws(" ", first_name, middle_name, last_name) as 'Full Name' 
FROM
    employees
WHERE
    salary IN (25000, 14000, 12500, 23600);
    
 -- 10
 
 SELECT 
    first_name, last_name
FROM
    employees
WHERE
    manager_ID IS NULL;
    
-- 11

SELECT 
    first_name, last_name, salary
FROM
    employees
WHERE
    salary > 50000
ORDER BY salary DESC;
 
 -- 12
SELECT 
    first_name, last_name
FROM
    employees
ORDER BY salary DESC
LIMIT 5;

-- 13
SELECT 
    first_name, last_name
FROM
    employees
WHERE
    department_id != 4
;

-- 14

SELECT 
employee_id AS 'ID',
    first_name AS 'First Name',
    last_name AS 'Last Name',
     middle_name AS 'Middle Name',
    job_title AS 'Job Title',
    department_id AS 'Dept ID',
    manager_id AS 'Mngr ID',
    hire_date AS 'Hire Date',
    salary,
    address_id AS 'address'
FROM
    employees
order by  salary desc , first_name asc, last_name desc, middle_name asc, employee_id ; 

-- 15
CREATE VIEW v_employees_salaries AS
    SELECT 
        first_name, last_name, salary
    FROM
        employees;
       
-- 16
CREATE VIEW v_employees_job_titles AS
    SELECT 
        CONCAT_WS(' ', first_name, middle_name, last_name) AS 'full-name',
        job_title
    FROM
        employees;
        
-- 17
select distinct (job_title)
from employees
order by job_title;

-- 18
SELECT 
    *
FROM
    projects
ORDER BY start_date , name
LIMIT 10;

-- 19

SELECT 
    first_name, last_name, hire_date
FROM
    employees
ORDER BY hire_date DESC
LIMIT 7;

-- 20

Update  employees 
Set salary = salary * 1.12
where department_id IN (1 , 2, 4, 11);
select salary
from employees;

use geography;
-- 21 

SELECT 
    peak_name
FROM
    peaks
ORDER BY peak_name;

-- 22

SELECT 
    country_name, population
FROM
    countries
WHERE
    continent_code = 'EU'
ORDER BY population DESC, country_name
LIMIT 30;

-- 23

SELECT 
    country_name,
    country_code,
    IF(currency_code = 'EUR',
        'Euro',
        'Not Euro')
FROM
    countries
ORDER BY country_name;


use diablo;
-- 24
SELECT 
    name
FROM
    characters
ORDER BY name;







 
 