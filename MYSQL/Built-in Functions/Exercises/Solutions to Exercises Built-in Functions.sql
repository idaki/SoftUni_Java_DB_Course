use soft_uni;

-- 1
SELECT first_name, last_name
FROM employees
WHERE first_name like 'Sa%'
ORDER BY employee_id;

-- 2
SELECT first_name, last_name
FROM employees
WHERE last_name LIKE '%ei%'
ORDER BY employee_id;


-- 3
SELECT 
    first_name
FROM
    employees
WHERE
    department_id IN (3 , 10)
        AND YEAR(hire_date) BETWEEN 1995 AND 2005
ORDER BY employee_id;

-- 4

SELECT 
    first_name, last_name
FROM
    employees
WHERE
    job_title NOT LIKE LOWER('%engineer%')
ORDER BY employee_id;

-- 5

SELECT name
FROM towns
WHERE char_length(name) = 5 or char_length(name) =6
ORDER BY name;

-- 6
SELECT town_id, name
FROM towns 
WHERE left(lower(name),1) in ('m','k','b','e')
order by name;

-- 7

SELECT 
    town_id, name
FROM
    towns
WHERE
    LEFT(LOWER(name), 1) NOT IN ('r' , 'b', 'd')
ORDER BY name;

-- 8 
CREATE VIEW v_employees_hired_after_2000 AS
    SELECT 
        first_name, last_name
    FROM
        employees
    WHERE
        YEAR(hire_date) > 2000;
        
-- 9 
SELECT 
    first_name, last_name
FROM
    employees
WHERE
    CHAR_LENGTH(last_name) = 5;

-- geography database

use geography;

-- 10 

SELECT country_name, iso_code
FROM countries
where country_name  like 'a%a%a%' 
or  country_name  like '%a%a%a%'
order by iso_code;

-- Diablo data base 
use diablo;

-- 11 
SELECT 
    p.peak_name,
    r.river_name,
    LOWER(CONCAT(p.peak_name, SUBSTRING(r.river_name, 2))) AS `mix`
FROM
    `peaks` AS `p`,
    `rivers` AS `r`
WHERE
    RIGHT(p.peak_name, 1) = LEFT(r.river_name, 1)
ORDER BY `mix`;
-- 12

SELECT 
    name, DATE_FORMAT(start, '%Y-%m-%d') as month
FROM
    games
WHERE
    YEAR(start) = 2011
        or YEAR(start) = 2012
ORDER BY start , name 
Limit 50;

-- 13
SELECT  
user_name,
right(email, char_length(email)- INSTR(email, '@') )  as `email provider`

FROM users
ORDER BY `email provider`, user_name;

-- 14 

SELECT user_name, ip_address
from users
WHERE ip_address like '___.1%.%.___'
order by user_name;

-- 15
select `name` as 'game',

CASE
  WHEN HOUR(start) >= 0 AND HOUR(start) < 12 THEN 'Morning'
 WHEN  HOUR(start)>= 12 AND HOUR(start) < 18 THEN 'Afternoon' 
else  'Evening'
END as 'Part of the Day',

CASE
WHEN duration<= 3 THEN 'Extra Short'
WHEN duration >3 AND duration<=6 THEN 'Short'
WHEN duration >6 AND duration<=10 THEN 'Long'
ELSE 'Extra Long'
END as 'Duration'

FROM games;

-- Orders
use orders;

-- 16

SELECT 
    
    product_name,
    order_date,
    DATE_ADD(order_date, INTERVAL 3 DAY) AS pay_day,
    DATE_ADD(order_date, INTERVAL 1 MONTH) AS deliver_due
FROM
    orders;







