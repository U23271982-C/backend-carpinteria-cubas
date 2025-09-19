package com.content.trabajador_servicio.service;

import com.content.trabajador_servicio.mapper.mapperImpl.EmployeeMapper;
import com.content.trabajador_servicio.repository.EmployeeRepository;
import com.content.trabajador_servicio.service.abstractService.ServiceAbs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements ServiceAbs {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Object create(Object dto) {
        return null;
    }

    @Override
    public List readById(Long id) {
        return List.of();
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update() {

    }
}
