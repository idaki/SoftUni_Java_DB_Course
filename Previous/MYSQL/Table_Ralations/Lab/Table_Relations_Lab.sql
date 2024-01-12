select *
from rooms;

select *
from routes;

select *
from vehicles;
select *
from campers;


-- 1
create table mountains(
id int AUTO_INCREMENT primary key,
name varchar(50) not null
) ;

create table peaks(
id int AUTO_INCREMENT primary key,
name varchar(50) not null,
mountain_id int not null,
CONSTRAINT fk_peaks_mountaines
FOREIGN KEY (mountain_id)
REFERENCES mountains(id)
);

-- 2

SELECT 
    v.driver_id,
    v.vehicle_type,
    CONCAT_WS(' ',first_name, last_name) AS driver_name
FROM
    vehicles AS v
        JOIN
    campers AS c ON v.driver_id = c.id;
    
    
    -- 3
    
   SELECT 
    starting_point,
    end_point,
    leader_id,
    CONCAT_WS(' ', first_name, last_name)
FROM
    routes AS r
        JOIN
    campers AS c ON r.leader_id = c.id;
    
    
    -- 4
    
CREATE TABLE peaks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    mountain_id INT,
    CONSTRAINT `fk_mountain_id` FOREIGN KEY (`mountain_id`)
        REFERENCES `mountains` (`id`)
        ON DELETE CASCADE
);

    

