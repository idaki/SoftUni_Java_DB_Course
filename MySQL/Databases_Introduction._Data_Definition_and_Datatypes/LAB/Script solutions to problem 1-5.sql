create database gamebar;
use gamebar;

/*1. Create Tables */

create table employees
(
    id         INT primary key AUTO_INCREMENT,
    first_name varchar(50) not null,
    last_name  varchar(50) not null
);

create table categories
(
    id   INT primary key AUTO_INCREMENT,
    name varchar(50) not null
);

create table products
(
    id          INT primary key AUTO_INCREMENT,
    name        varchar(50) not null,
    category_id INT         NOT Null
);

/*2. Insert Data in Tables*/

insert into employees(first_name, last_name)
 Values   ('John', 'Smith'),
('Peter', 'Pan'),
('David', 'Beckham');

/*3. Alter Tables*/

alter table employees
   add column middle_name varchar(50);

/*4. Adding Constraints*/

alter table products
ADD FOREIGN KEY (category_id)
REFERENCES categories (id);

/**/
alter table employees
    CHANGE middle_name
        middle_name VARCHAR(100) NOT NULL;








