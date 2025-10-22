package com.content.customer_service.service;

import com.content.customer_service.dto.request.DepartmentRequestDTO;
import com.content.customer_service.dto.response.DepartmentResponseDTO;
import com.content.customer_service.exception.EServiceLayer;
import com.content.customer_service.mapper.mapperImpl.DepartmentMapper;
import com.content.customer_service.model.Department;
import com.content.customer_service.model.StateEntity;
import com.content.customer_service.repository.DepartmentRepository;
import com.content.customer_service.service.abstractService.ServiceAbs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService implements ServiceAbs<DepartmentRequestDTO, DepartmentResponseDTO> {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Transactional
    @Override
    public DepartmentResponseDTO create(DepartmentRequestDTO dto) {
        log.info("Create department {}", dto);
        Department model = departmentMapper.toModel(dto);
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build()); // ACTIVO
        model = departmentRepository.save(model);
        return departmentMapper.toDTO(model);
    }

    @Transactional
    @Override
    public List<DepartmentResponseDTO> allList() {
        log.info("DepartmentService.allList()");
        //Muestra toda la lista de departamentos menos los eliminados
        return departmentRepository.findAll().stream()
                .filter(Department -> Department.getState_entity_id().getState_entity_id() != 3)
                .map(departmentMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public DepartmentResponseDTO readByUUID(UUID uuid) {
        log.info("DepartmentService.readByUUID()");
        Department model = searchEntityByUUID(uuid);
        return departmentMapper.toDTO(model);
    }

    @Transactional
    @Override
    public void remove(UUID uuid) {
        log.info("DepartmentService.remove()");
        Department model_existing = searchEntityByUUID(uuid);
        model_existing.setState_entity_id(StateEntity.builder().state_entity_id(3).build()); // ELIMINADO
        departmentRepository.save(model_existing);
    }

    @Transactional
    @Override
    public DepartmentResponseDTO updateByUUID(UUID uuid, DepartmentRequestDTO dto) {
        log.info("DepartmentService.updateByUUID()");
        Department model_existing = searchEntityByUUID(uuid);
        // Actualizamos los campos del departamento
        departmentMapper.updateFromDto(dto, model_existing);
        Department model_updated = departmentRepository.save(model_existing);
        return departmentMapper.toDTO(model_updated);
    }

    /**
     * Método privado para buscar una entidad Departamento
     * por su UUID */
    private Department searchEntityByUUID(UUID uuid) {
        log.info("DepartmentService.searchEntityByUUID()");
        return departmentRepository.findByUuid(uuid)
                .orElseThrow(() -> new EServiceLayer(String.format("Departamento no encontrado con UUID: " + uuid)));
    }
}
