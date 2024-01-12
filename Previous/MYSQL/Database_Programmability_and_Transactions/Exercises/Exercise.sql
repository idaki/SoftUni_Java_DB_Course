
use soft_uni;



#1
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
    BEGIN
        SELECT first_name, last_name FROM employees
        WHERE  salary >35000
         order by first_name,last_name, employee_id;
    END $$

DELIMITER ;

call usp_get_employees_salary_above_35000();

#2
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(salary_limit DOUBLE(19,4))
BEGIN
    SELECT e.first_name, e.last_name
    FROM `employees` AS e
    WHERE e.salary >= salary_limit
    ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;

CALL usp_get_employees_salary_above(48100);

#3
DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(name_start TEXT)
BEGIN
    SELECT t.name AS 'town_name'
    FROM `towns` AS t
    WHERE t.name LIKE concat(name_start,'%')
    ORDER BY t.name;
END $$
DELIMITER ;

CALL usp_get_towns_starting_with('b');
CALL usp_get_towns_starting_with('be');
CALL usp_get_towns_starting_with('berlin');


#4
DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(town_name TEXT)
BEGIN
    SELECT e.first_name, e.last_name
    FROM `employees` AS e
             JOIN `addresses` AS a ON e.address_id = a.address_id
             JOIN  `towns` AS t ON a.town_id = t.town_id
    WHERE t.name = town_name
    ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;

CALL usp_get_employees_from_town('Sofia');


#5

CREATE FUNCTION ufn_get_salary_level(salary DOUBLE(19,4))
    RETURNS VARCHAR(7)
    RETURN (
        CASE
            WHEN salary < 30000 THEN 'Low'
            WHEN salary <= 50000 THEN 'Average'
            ELSE 'High'
            END
        );

SELECT ufn_get_salary_level(13500);
SELECT ufn_get_salary_level(43300);
SELECT ufn_get_salary_level(125500);

DROP FUNCTION IF EXISTS ufn_get_salary_level;
#6
DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(7))
BEGIN
    SELECT e.first_name, e.last_name
    FROM `employees` AS e
    WHERE e.salary < 30000 AND salary_level = 'low'
       OR e.salary >= 30000 AND e.salary <= 50000 AND salary_level = 'average'
       OR e.salary > 50000 AND salary_level = 'high'
    ORDER BY e.first_name DESC, e.last_name DESC;
END $$
DELIMITER ;


#7
CREATE FUNCTION ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))
    RETURNS BIT
    RETURN word REGEXP (concat('^[', set_of_letters, ']+$'));

SELECT ufn_is_word_comprised('oistmiahf', 'Sofia');
SELECT ufn_is_word_comprised('oistmiahf', 'halves');
SELECT ufn_is_word_comprised('bobr', 'Rob');
SELECT ufn_is_word_comprised('pppp', 'Guy');
#8
ALTER TABLE `departments` DROP FOREIGN KEY `fk_departments_employees`;
ALTER TABLE `departments` DROP INDEX `fk_departments_employees` ;
ALTER TABLE `employees_projects` DROP FOREIGN KEY `fk_employees_projects_employees`;
ALTER TABLE `employees` DROP FOREIGN KEY `fk_employees_employees`;

DELETE FROM `employees`
WHERE
        `department_id` IN (SELECT
                                d.department_id
                            FROM
                                `departments` AS d
                            WHERE
                                    d.name IN ('Production' , 'Production Control'));

DELETE FROM `departments`
WHERE
        `name` IN ('Production' , 'Production Control');
#9
DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
    SELECT
        CONCAT_WS(' ', h.first_name, h.last_name) AS 'full_name'
    FROM
        `account_holders` AS h
            JOIN
        (SELECT DISTINCT
             a.account_holder_id
         FROM
             `accounts` AS a) as a ON h.id = a.account_holder_id
    ORDER BY `full_name`;
END $$
DELIMITER ;

CALL usp_get_holders_full_name();
#10
DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(balance DECIMAL(19, 4))
BEGIN
    SELECT
        h.first_name, h.last_name
    FROM
        `account_holders` AS h
            JOIN
        (SELECT
             a.id, a.account_holder_id, SUM(a.balance) AS 'total_balance'
         FROM
             `accounts` AS a
         GROUP BY (a.account_holder_id)
         HAVING `total_balance` > balance) as a ON h.id = a.account_holder_id
    ORDER BY a.id;
END $$
DELIMITER ;

CALL usp_get_holders_with_balance_higher_than(7000);
#11
DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(
    initial_sum DECIMAL(19, 4), interest_rate DECIMAL(19, 4), years INT)
    RETURNS DECIMAL(19, 4)
-- RETURNS DOUBLE(19, 2) -- Judge
BEGIN
    RETURN initial_sum * POW((1 + interest_rate), years);
END $$
DELIMITER ;

SELECT ufn_calculate_future_value(1000, 0.1, 5);


#12


DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account(
    account_id INT, interest_rate DECIMAL(19, 4))
BEGIN
    SELECT
        a.id AS 'account_id', h.first_name, h.last_name, a.balance AS 'current_balance',
        ufn_calculate_future_value(a.balance, interest_rate, 5) AS 'balance_in_5_years'
    FROM
        `account_holders` AS h
            JOIN
        `accounts` AS a ON h.id=a.account_holder_id
    WHERE a.id = account_id;
END $$
DELIMITER ;


#13

DELIMITER $$
CREATE PROCEDURE usp_deposit_money(
    account_id INT, money_amount DECIMAL(19, 4))
BEGIN
    IF money_amount > 0 THEN
        START TRANSACTION;

        UPDATE `accounts` AS a
        SET
            a.balance = a.balance + money_amount
        WHERE
                a.id = account_id;

        IF (SELECT a.balance
            FROM `accounts` AS a
            WHERE a.id = account_id) < 0
        THEN ROLLBACK;
        ELSE
            COMMIT;
        END IF;
    END IF;
END $$
DELIMITER ;


#14

DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(
    account_id INT, money_amount DECIMAL(19, 4))
BEGIN
    IF money_amount > 0 THEN
        START TRANSACTION;

        UPDATE `accounts` AS a
        SET
            a.balance = a.balance - money_amount
        WHERE
                a.id = account_id;

        IF (SELECT a.balance
            FROM `accounts` AS a
            WHERE a.id = account_id) < 0
        THEN ROLLBACK;
        ELSE
            COMMIT;
        END IF;
    END IF;
END $$
DELIMITER ;

CALL usp_withdraw_money(1, 10);

SELECT
    a.id AS 'account_id', a.account_holder_id, a.balance
FROM
    `accounts` AS a
WHERE
        a.id = 1;

DROP PROCEDURE IF EXISTS usp_withdraw_money;