package com.content.employee_service.service;

import com.content.employee_service.dto.Request.EmployeeRequestDTO;
import com.content.employee_service.dto.Response.EmployeeResponseDTO;
import com.content.employee_service.mapper.mapperImpl.EmployeeMapper;
import com.content.employee_service.repository.EmployeeRepository;
import com.content.employee_service.service.abstractService.ServiceAbs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements ServiceAbs<EmployeeRequestDTO, EmployeeResponseDTO> {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDTO create(EmployeeRequestDTO dto) {
        return null;
    }

    @Override
    public EmployeeResponseDTO readById(Long id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<EmployeeResponseDTO> allList() {
        return List.of();
    }

    @Override
    public EmployeeResponseDTO update(int id, EmployeeRequestDTO dto) {
        return null;
    }

}
