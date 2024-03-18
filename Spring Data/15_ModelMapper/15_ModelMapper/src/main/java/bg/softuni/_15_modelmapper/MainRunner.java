package bg.softuni._15_modelmapper;

import bg.softuni._15_modelmapper.models.dtos.EmployeeInfoDTO;
import bg.softuni._15_modelmapper.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MainRunner implements CommandLineRunner {

    private EmployeeService employeeService;

    private ModelMapper mapper;

    public MainRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

//        Address address = new Address("Bg", "Plovdiv", "Marica");
//        Employee employee = new Employee("First", "Last", BigDecimal.TEN, LocalDate.now(), address);
//
//        BasicEmployeeDTO employeeDto = modelMapper.map(employee, BasicEmployeeDTO.class);

        List<EmployeeInfoDTO> list = employeeService.findInfoForBornBefore(LocalDate.of(1990, 01, 01));

        System.out.println("HELLO WORLD!!!");
    }
}
