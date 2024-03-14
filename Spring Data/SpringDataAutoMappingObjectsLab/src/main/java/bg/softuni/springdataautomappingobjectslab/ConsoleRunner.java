package bg.softuni.springdataautomappingobjectslab;

import bg.softuni.springdataautomappingobjectslab.DTOs.EmployeeDTO;
import bg.softuni.springdataautomappingobjectslab.entities.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<EmployeeDTO, Employee> employeeMap = new PropertyMap<EmployeeDTO, Employee>()
        {
            @Override
            protected void configure() {
                map().setFirstName(source.getFirstName());
// Add mappings for other fields
                map().setAddress(source.getAddressCity().);
            }
        };
        modelMapper.addMappings(employeeMap).map(employeeDto,employee);
        ConsoleRunner.java
        15
    }
}
