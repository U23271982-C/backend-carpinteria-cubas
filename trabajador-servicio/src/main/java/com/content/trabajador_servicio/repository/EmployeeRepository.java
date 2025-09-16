package com.content.trabajador_servicio.repository;

import com.content.trabajador_servicio.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
