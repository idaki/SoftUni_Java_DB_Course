use hotel;
-- 1 

select  id, first_name, last_name, job_title
from employees
order by id;
-- 2
SELECT 
    id, concat_ws(" ",first_name,last_name), job_title, salary
FROM
    employees
WHERE
    salary > 1000.00
ORDER BY id; 
-- 3
UPDATE employees
SET salary = salary + 100
WHERE job_title = 'Manager';
SELECT salary
FROM employees;

-- 4

SELECT 
    *
FROM
    employees
ORDER BY salary DESC
LIMIT 1;

-- 5

select * 
From employees 
where department_id = 4 and salary>=1000;

-- 6
DELETE FROM employees 
WHERE
    department_id = 2 OR department_id = 1; 
    select*
    from employees;
    