package bg.softuni.repository;


import bg.softuni.data.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    Set<Car> findAllByMakeOrderByTravelledDistanceDesc(String toyota);
}
