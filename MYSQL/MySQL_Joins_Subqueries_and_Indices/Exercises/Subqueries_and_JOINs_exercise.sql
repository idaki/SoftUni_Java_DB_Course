use soft_uni;

#1
select e.employee_id, e.job_title, e.address_id, a.address_text
from employees as e
         join addresses as a on e.address_id = a.address_id
order by e.address_id
limit 5;

#2
select first_name, last_name, t.name as town, a.address_text
from employees as e
         join addresses as a on e.address_id = a.address_id
         join towns as t on a.town_id = t.town_id
order by e.first_name, e.last_name
LIMIT 5;

#3
select e.employee_id, e.first_name, e.last_name, d.name
from employees as e
         join departments as d on e.department_id = d.department_id
where d.name = 'Sales'
order by e.employee_id desc;

#4
select e.employee_id, e.first_name, e.salary, d.name as department_name
from employees as e
         join departments as d on e.department_id = d.department_id
where e.salary > 15000
order by e.department_id desc
limit 5;


#5

select e.employee_id, e.first_name
from employees as e
         left join employees_projects as m on e.employee_id = m.employee_id
         left join projects as p on m.project_id = p.project_id
where name is null
order by e.employee_id desc
LIMIT 3;

#6

select e.first_name, e.last_name, e.hire_date, d.name
from employees as e
         join departments as d on e.department_id = d.department_id
where e.hire_date > '1999-01-01'
  and d.name in ('Sales', 'Finance')
order by e.hire_date;


#7
select e.employee_id, e.first_name, p.name
from employees as e
         join employees_projects as m on e.employee_id = m.employee_id
         join projects as p on m.project_id = p.project_id
where p.end_date is null
  and date(p.start_date) > '2002-08-13'
order by e.first_name, p.name
LIMIT 5;

#8
select e.employee_id, e.first_name, if(date(p.start_date) >= '2005-01-01', null, p.name)
from employees as e
         join employees_projects as m on e.employee_id = m.employee_id
         join projects as p on m.project_id = p.project_id
where e.employee_id = 24
order by p.name;

#9

select e.employee_id, e.first_name, e.manager_id, m.first_name
from employees as e
         join employees as m on e.manager_id = m.employee_id
where e.manager_id in (3, 7)
order by e.first_name;

#10
select e.employee_id,
       concat_ws(' ', e.first_name, e.last_name) as employee_name,
       concat_ws(' ', m.first_name, m.last_name) as manager_name,
       d.name
from employees as e
         join employees as m on e.manager_id = m.employee_id
         join departments as d on e.department_id = d.department_id
order by e.employee_id
limit 5;

# 11

select avg(salary) as min_average_salary
from employees
Group By department_id
order by min_average_salary
limit 1;

# 11
select MIN(t.salary)
from (select AVG(salary) as salary from employees group by department_id) as t;

#t represents new table  with average salary per department

use geography;


#12

select c.country_code,m.mountain_range,p.peak_name,p.elevation
from mountains as m
         join peaks as p on m.id = p.mountain_id
         join mountains_countries c on m.id = c.mountain_id
where c.country_code = 'BG' and p.elevation>2835
order by  p.elevation desc ;

#13

select c.country_code,count(m.id) as mountain_range
from mountains as m
         join mountains_countries as c on m.id = c.mountain_id
where c.country_code in ( 'BG', 'RU', 'US')
group by c.country_code
order by mountain_range desc ;


#14
select c.country_name,r.river_name
from countries as c
         left join countries_rivers as m on c.country_code = m.country_code
         left join rivers as r on m.river_id = r.id
where c.continent_code = 'AF'
order by c.country_name
LIMIT 5;

#15
SELECT
    continent_code, currency_code, COUNT(*) AS cnt
FROM
    countries AS c
GROUP BY c.continent_code , c.currency_code
HAVING cnt > 1
   AND cnt = (SELECT
                  COUNT(*)
              FROM
                  countries AS c2
              WHERE
                      c.continent_code = c2.continent_code
              GROUP BY currency_code
              ORDER BY COUNT(currency_code) DESC
              LIMIT 1)
ORDER BY c.continent_code , c.currency_code;


#16

select count(*) as country_count
from countries as c
         left join mountains_countries as m on c.country_code = m.country_code
where  mountain_id is null;

#17

SELECT country_name, MAX(elevation) AS highest_peak_elevation, MAX(length) AS longest_river_length
FROM countries AS c
         LEFT JOIN countries_rivers AS cr ON c.country_code = cr.country_code
         LEFT JOIN rivers as r on cr.river_id = r.id
         LEFT JOIN mountains_countries AS mc ON c.country_code = mc.country_code
         LEFT JOIN mountains AS m ON mc.mountain_id = m.id
         LEFT JOIN peaks AS p ON m.id = p.mountain_id
GROUP BY country_name
ORDER BY highest_peak_elevation DESC, longest_river_length DESC, country_name
LIMIT 5;

