#1


delimiter $$
CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(20))
    RETURNS INT
    DETERMINISTIC

    BEGIN
DECLARE e_count INT;
set e_count := (select count(e.employee_id)
                from employees as e
                         join addresses as a on e.address_id = a.address_id
                         join towns as t on a.town_id = t.town_id
                where t.name = town_name);
RETURN e_count;
END $$

DELIMITER ;
;

select e.salary, d.name from employees as e join departments as d on e.department_id = d.department_id;


DELIMITER $$

CREATE PROCEDURE usp_raise_salaries(department_name varchar(50))
BEGIN
    update employees as e
        join departments as d on e.department_id = d.department_id
    set salary = salary * 1.05
    where d.name = department_name;
End $$


#3

CREATE PROCEDURE usp_raise_salary_by_id(id int)
BEGIN
    START TRANSACTION;
    IF((SELECT count(employee_id) FROM employees WHERE employee_id
                                                           like id)<>1) THEN
        ROLLBACK;
    ELSE
        UPDATE employees AS e SET salary = salary * 1.05
        WHERE e.employee_id = id;
    END IF;
END$$

#4

create table deleted_employees(
    employee_id int primary key auto_increment,
     first_name varchar(50) not null,
     last_name varchar(50) not null,
     middle_name varchar(50) not null,
     job_title varchar(50) not null,
     department_id int not null ,
     salary DOUBLE not null );



Create TRIGGER tr_deleted_employees
after delete on employees
for each row
BEGIN
    INSERT INTO deleted_employees (first_name,last_name,
                                   middle_name,job_title,department_id,salary)
    VALUES(OLD.first_name,OLD.last_name,OLD.middle_name,
           OLD.job_title,OLD.department_id,OLD.salary);
END

