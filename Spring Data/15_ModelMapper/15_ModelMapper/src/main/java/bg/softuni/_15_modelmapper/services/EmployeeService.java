package bg.softuni._15_modelmapper.services;

import bg.softuni._15_modelmapper.models.dtos.EmployeeInfoDTO;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<EmployeeInfoDTO> findInfoForBornBefore(LocalDate before);
}
