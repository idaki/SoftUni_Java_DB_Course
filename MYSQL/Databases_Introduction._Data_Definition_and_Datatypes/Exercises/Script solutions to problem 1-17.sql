create schema miions;
use minions;
-- 1
CREATE TABLE minions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT NOT NULL
);

CREATE TABLE towns (
    town_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

-- 2
ALTER table minions
Add column town_id INT,
ADD FOREIGN KEY town_id_foreign 
(town_id)
 REFERENCES towns (id);
 
 -- 3
INSERT INTO `towns` (`id`, `name`) VALUES
(1, 'Sofia'),
(2, 'Plovdiv'),
(3, 'Varna');

INSERT INTO `minions` (`id`, `name`, `age`, `town_id`) VALUES
(1, 'Kevin', 22, 1),
(2, 'Bob', 15, 3),
(3, 'Steward', NULL, 2);SELECT 
    *
FROM
    minions;
-- 4 
truncate minions.minions; 

-- 5
Drop Table minions;
Drop Table towns;

-- 6
CREATE TABLE people (
    id INT(241) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    picture BLOB,
    height FLOAT(7 , 2 ),
    weight FLOAT(7 , 2 ),
    gender CHAR(1) NOT NULL,
    birthdate DATE NOT NULL,
    biography TEXT
);

INSERT INTO people (name, height, weight, gender,birthdate )
VALUES 
('John', 190, 90,'m', curdate()),
('Peter', 190, 90,'m', curdate()),
('Anna', 165, 50,'f', curdate()),
('Jane', 175, 52,'f', curdate()),
('Lena', 170, 57,'f', curdate())
;


-- 7 
use minions; 


CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30)CHARACTER SET UTF8 COLLATE UTF8MB3_GENERAL_CI NOT NULL UNIQUE,
    password VARCHAR(26)CHARACTER SET UTF8 COLLATE UTF8MB3_GENERAL_CI NOT NULL,
    profile_picture BLOB,
    last_login_time DATETIME,
    is_deleted ENUM('true', 'false') NOT NULL DEFAULT 'false'
);
INSERT INTO users (username,password)
VALUES
('a','1'),
('b','2'),
('c','3'),
('d','4'),
('e','5');

select * from minions.users;

-- 8
ALTER TABLE users
DROP PRIMARY KEY, 
ADD CONSTRAINT pk_users
PRIMARY KEY (id, username);

 select * from minions.users;

-- 9
ALTER table users
CHANGE COLUMN last_login_time  last_login_time DATETIME DEFAULT NOW();

-- 10

ALTER TABLE users
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users
PRIMARY KEY (id),
ADD CONSTRAINT uk_users
UNIQUE (username);

-- 11

create database Movies; 
use Movies; 

CREATE TABLE directors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    director_name VARCHAR(50) NOT NULL,
    notes TEXT
);

CREATE TABLE genres (
    id INT PRIMARY KEY AUTO_INCREMENT,
    genre_name VARCHAR(50) NOT NULL,
    notes TEXT
);

CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL,
    notes TEXT
);


CREATE TABLE movies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    director_id INT NOT NULL,
    copyright_year YEAR,
    length INT(3),
    genre_id INT NOT NULL,
    category_id INT NOT NULL,
    rating FLOAT(4 , 2 ),
    notes TEXT,
    CONSTRAINT fk_movies_directors FOREIGN KEY (director_id)
        REFERENCES directors (id),
    CONSTRAINT fk_movies_genre FOREIGN KEY (genre_id)
        REFERENCES genres (id),
    CONSTRAINT fk_movies_categories FOREIGN KEY (category_id)
        REFERENCES categories (id)
);

INSERT INTO directors(director_name, notes)
VALUES 
('John', 'ABC'),
('Bas', 'CBA'),
('Kim', 'ACB'),
('Ana', 'ACB'),
('Jim', 'CAB');

INSERT INTO genres(genre_name, notes)
VALUES
('comedy', 'fun'),
('drama', 'emotional'),
('sci-si', 'inspirational'),
('action', 'fast'),
('horror', 'scary');


INSERT INTO `categories` (`id`,`category_name`) VALUES
(1, 'comedy'),
(2, 'drama'),
(3, 'sci-si'),
(4, 'action'),
(5, 'horror');

INSERT INTO `movies` VALUES
(1, 'ABC', 1, '2011', 238, 5, 3, 7.1, 'OK'),
(2, 'DEF', 2, '2024', 140, 1, 4, 8.2, 'TOP'),
(3, 'GHI', 3, '2013', 133, 2, 5, 7.4, NULL),
(4, 'JKL', 4, '2001', 119, 3, 4, 5.1, 'AVG'),
(5, 'MNO', 5, '2004', 149, 1, 5, 8.1, NULL);

-- 12
create database car_rental;
use car_rental;

CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(20) NOT NULL,
    daily_rate FLOAT(5 , 2 ) NOT NULL,
    weekly_rate FLOAT(7 , 2 ) NOT NULL,
    monthly_rate FLOAT(10 , 2 ) NOT NULL,
    weekend_rate FLOAT(7 , 2 ) NOT NULL
);

INSERT INTO  categories VALUES
(1, 'A', 1.1, 7.7, NULL, 2.2),
(2, 'B', 1.5, 9.5, NULL, 3.0),
(3, 'C', 2.1, 14.7, NULL, 12.2);

CREATE TABLE cars(
id INT PRIMARY KEY AUTO_INCREMENT,
plate_number VARCHAR(10) NOT NULL UNIQUE,
make VARCHAR(10),
model VARCHAR(10) NOT NULL,
car_year YEAR,
category_id INT NOT NULL,
doors INT(1) NOT NULL,
picture BLOB,
car_condition VARCHAR(20),
available BIT
);
INSERT INTO cars VALUES
(1, 'A1234AA', NULL, 'SEDAN', '2020', 1, 5, NULL, 'NEW', 1),
(2, 'B1234BB', NULL, 'COUPE', '2010', 2, 3, NULL, 'OLD', 1),
(3, 'C1233CC', NULL, 'BUS', '2019', 3, 3, NULL, 'NEW', 1);

CREATE TABLE employees(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
title VARCHAR(20) NOT NULL,
notes TEXT
);
INSERT INTO employees VALUES
(1, 'Ivan', 'Ivanov', 'sales', NULL),
(2, 'Peter', 'Petrov', 'support', NULL),
(3, 'Kaloyan', 'Dimitrov', 'mechanic', NULL);

CREATE TABLE customers(
id INT PRIMARY KEY AUTO_INCREMENT,
driver_licence_number INT NOT NULL UNIQUE,
full_name VARCHAR(20) NOT NULL,
address VARCHAR(100),
city VARCHAR(20) NOT NULL,
zip_code VARCHAR(10),
notes TEXT
);
INSERT INTO `customers` VALUES
(1, '123321123', 'Koko Kokov', NULL, 'Sofia', '1212', NULL),
(2, '123456789', 'Bob Bobov', NULL, 'Bobbovo', '4444', NULL),
(3, '987654321', 'Roro Rorov', NULL, 'Rorovo', '8899', NULL);

CREATE TABLE rental_orders(
id INT PRIMARY KEY AUTO_INCREMENT,
employee_id INT,
customer_id INT,
car_id INT,
car_condition VARCHAR(20),
tank_level FLOAT,
kilometrage_start INT,
kilometrage_end INT,
total_kilometrage INT,
start_date DATE,
end_date DATE,
total_days INT,
rate_applied VARCHAR(20),
tax_rate INT,
order_status VARCHAR(20),
notes TEXT
-- ,
-- CONSTRAINT fk_rental_orders_employees
-- FOREIGN KEY (`employee_id`) REFERENCES `employees`(`id`),
-- CONSTRAINT fk_rental_orders_customers
-- FOREIGN KEY (`customer_id`) REFERENCES `customers`(`id`),
-- CONSTRAINT fk_rental_orders_cars
-- FOREIGN KEY (`car_id`) REFERENCES `cars`(`id`)
);
INSERT INTO rental_orders VALUES
(1, 1, 1, 1, 'NEW', 60.8, 12000, 12500, 12500, '2008-11-11', '2008-11-21', 10, NULL, NULL, NULL, NULL),
(2, 2, 2, 2, 'OLD', 90.8, 120000, 120500, 120500, '2008-11-11', '2008-11-21', 10, NULL, NULL, NULL, NULL),
(3, 3, 3, 3, 'NEW', 150.8, 100000, 125000, 125000, '2008-11-11', '2008-11-21', 10, NULL, NULL, NULL, NULL);


-- 13


create database soft_uni;
use soft_uni;

CREATE TABLE towns (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);


CREATE TABLE addresses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adress_text TEXT NOT NULL,
    town_id INT NOT NULL,
    CONSTRAINT fk_addressses_towns FOREIGN KEY (town_id)
        REFERENCES towns (id)
);

create table departments (
id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
    );
   CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    job_title VARCHAR(50) NOT NULL,
    hire_date DATE ,
    salary FLOAT(10 , 2 ),
    department_id INT NOT NULL, 
    address_id INT  , 
     CONSTRAINT fk_employees_departments FOREIGN KEY (department_id)
        REFERENCES departments (id),
    CONSTRAINT fk_employees_addresses FOREIGN KEY (address_id)
        REFERENCES addresses (id)
);
 
INSERT INTO towns(name) 
VALUES 
('Sofia'),
('Plovdiv'),
('Varna'),
('Burgas');


INSERT INTO departments (name) 
VALUES 
('Engineering'),
('Sales'),
('Marketing'),
('Software Development'),
('Quality Assurance');



INSERT INTO employees  (first_name, middle_name, last_name, job_title, department_id, hire_date, salary, address_id) 
VALUES
('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00, NULL),
('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00, NULL),
('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25, NULL),
('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00, NULL),
('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88, NULL);
 
-- 14
select * from towns; 
select * from departments;
select * from employees;

-- 15
SELECT 
    *
FROM
    towns
ORDER BY name ASC;

SELECT 
    *
FROM
    departments
ORDER BY name ASC;
SELECT 
    *
FROM
    employees
ORDER BY salary DESC;


-- 16
SELECT 
    name
FROM
    towns
ORDER BY name ASC;
    
SELECT 
    name
FROM
    departments
ORDER BY name ASC;
    
SELECT 
    first_name, last_name, job_title, salary
FROM
    employees
ORDER BY salary DESC;

-- 17

UPDATE employees
SET salary = salary * 1.10;
SELECT salary
FROM employees;


