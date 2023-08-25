use gringotts;

-- 1
SELECT count(last_name = 'Mr.Bodrog') as count
from wizzard_deposits;

-- 2
SELECT 
    magic_wand_size
FROM
    wizzard_deposits
ORDER BY magic_wand_size DESC
LIMIT 1;

-- 3

SELECT 
    deposit_group, MAX(magic_wand_size) AS longest_magic_wand
FROM
    wizzard_deposits
GROUP BY deposit_group
ORDER BY longest_magic_wand , deposit_group;

-- 4 

SELECT 
    deposit_group
FROM
    wizzard_deposits
GROUP BY deposit_group
ORDER BY AVG(magic_wand_size)
LIMIT 1;

-- 5

SELECT 
    deposit_group, ROUND(SUM(deposit_amount), 2) AS total_sum
FROM
    wizzard_deposits
GROUP BY deposit_group
ORDER BY total_sum;

-- 6



SELECT 
    deposit_group, ROUND(SUM(deposit_amount), 2) AS total_sum
FROM
    wizzard_deposits
where magic_wand_creator = 'Ollivander family'
GROUP BY deposit_group
ORDER BY deposit_group;

-- 7
SELECT 
    deposit_group, ROUND(SUM(deposit_amount), 2) AS total_sum
FROM
    wizzard_deposits
where magic_wand_creator = 'Ollivander family'
GROUP BY deposit_group
having `total_sum`<150000
ORDER BY `total_sum` desc;

-- 8 

SELECT `deposit_group`, `magic_wand_creator`, MIN(`deposit_charge`) AS `min_deposit_charge`
FROM `wizzard_deposits`
GROUP BY `deposit_group`, `magic_wand_creator`
ORDER BY `magic_wand_creator`, `deposit_group`;

-- 9 

SELECT 
    (CASE
        WHEN age >= 0 AND age <= 10 THEN '[0-10]'
        WHEN age >= 11 AND age <= 20 THEN '[11-20]'
        WHEN age >= 21 AND age <= 30 THEN '[21-30]'
        WHEN age >= 31 AND age <= 40 THEN '[31-40]'
        WHEN age >= 41 AND age <= 50 THEN '[41-50]'
        WHEN age >= 51 AND age <= 60 THEN '[51-60]'
        ELSE '[61+]'
    END) AS age_group,
    COUNT(id) AS `wizard_count`
FROM
    wizzard_deposits
group by age_group
order by wizard_count;


-- 10

SELECT 
    LEFT(first_name, 1) AS first_letter
FROM
    wizzard_deposits
WHERE
    deposit_group = 'Troll Chest'
GROUP BY first_letter
ORDER BY first_letter;

-- 11

select *
from wizzard_deposits; 

SELECT `deposit_group`, `is_deposit_expired`, AVG(`deposit_interest`) AS `average_interest`
FROM `wizzard_deposits`
WHERE `deposit_start_date` > '1985-01-01'
GROUP BY `deposit_group`, `is_deposit_expired`
ORDER BY `deposit_group` DESC, `is_deposit_expired`;

use soft_uni;
-- 12

select *
from employees;

select department_id, min(salary) as minimum_salary
from employees 
where department_id IN (2,5,7) and hire_date> 2000-01-01
group by department_id  
order by department_id;

-- 13

create table employees_salary_more_than_30000
SELECT *
from employees
where salary > 30000; 

Delete from employees_salary_more_than_30000
where manager_id = 42;

Update employees_salary_more_than_30000
set salary = salary + 5000
where department_id = 1;

select department_id, AVG(salary)
from employees_salary_more_than_30000
group by department_id
order by department_id;

-- 14

select department_id, max(salary) as max_salary
from employees
GROUP BY department_id
having max_salary not between 30000 and 70000
order by department_id;


-- 15
select count(employee_id) as ''
from employees 
where manager_id is null;

-- 16 

SELECT 
    department_id,
    (SELECT 
            salary
        FROM
            employees AS e2
        WHERE
            e1.department_id = e2.department_id
        ORDER BY salary DESC
        LIMIT 1 OFFSET 2) AS third_highest_salary
FROM
    employees AS e1
GROUP BY department_id
ORDER BY department_id;

-- 18
SELECT 
    department_id, SUM(salary) AS total_salary
FROM
    employees
GROUP BY department_id
ORDER BY department_id;
