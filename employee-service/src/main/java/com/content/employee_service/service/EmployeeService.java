package com.content.employee_service.service;

import com.content.employee_service.dto.request.EmployeeRequestDTO;
import com.content.employee_service.dto.response.EmployeeResponseDTO;
import com.content.employee_service.mapper.mapperImpl.EmployeeMapper;
import com.content.employee_service.service.abstractService.ServiceAbs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService implements ServiceAbs<EmployeeRequestDTO, EmployeeResponseDTO> {

    //private final EmployeeRepository employeeRepository;
    //private final EmployeeMapper employeeMapper;
    private final EmployeeMapper employeeMapper;

    @Transactional
    @Override
    public EmployeeResponseDTO create(EmployeeRequestDTO dto) {

        return null;
    }

    @Override
    public EmployeeResponseDTO readByUUID(UUID uuid) {
        return null;
    }

    @Override
    public void remove(UUID uuid) {

    }

    @Override
    public List<EmployeeResponseDTO> allList() {
        return List.of();
    }

    @Override
    public EmployeeResponseDTO updateByUUID(UUID uuid, EmployeeRequestDTO dto) {

        return null;
    }
}
