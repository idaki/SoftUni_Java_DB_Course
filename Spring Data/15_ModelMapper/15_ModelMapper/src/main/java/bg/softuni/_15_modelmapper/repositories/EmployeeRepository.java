package bg.softuni._15_modelmapper.repositories;

import bg.softuni._15_modelmapper.models.Employee;
import bg.softuni._15_modelmapper.models.dtos.EmployeeInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT new bg.softuni._15_modelmapper.models.dtos.EmployeeInfoDTO(e.firstName, e.lastName, e.salary, e.birthday) " +
            "FROM Employee e WHERE e.birthday < :before")
    List<EmployeeInfoDTO> findAllByBirthdayBefore(LocalDate before);

    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}
