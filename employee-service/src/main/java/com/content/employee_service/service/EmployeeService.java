package com.content.employee_service.service;

import com.content.employee_service.dto.request.EmployeeRequestDTO;
import com.content.employee_service.dto.response.EmployeeResponseDTO;
import com.content.employee_service.exception.EServiceLayer;
import com.content.employee_service.mapper.mapperImpl.*;
import com.content.employee_service.model.*;
import com.content.employee_service.repository.*;
import com.content.employee_service.service.abstractService.ServiceAbs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService implements ServiceAbs<EmployeeRequestDTO, EmployeeResponseDTO> {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    private final IdentificationTypeRepository identificationTypeRepository;

    private final PostRepository postRepository;

    private final ContractRepository contractRepository;

    private final DistricRepository districRepository;

    @Transactional
    @Override
    public EmployeeResponseDTO create(EmployeeRequestDTO dto) {
        log.info("EmployeeService.create()");

        // Corroboramos tipo de identificacion
        IdentificationType identification_type_reading =
                identificationTypeRepository.findByUuid(dto.getIdentification_type_uuid())
                        .orElseThrow(() -> new EServiceLayer("El tipo de identificación no existe"));
        // Corroboramos cargo
        Post post_reading =
                postRepository.findByUuid(dto.getPost_id_uuid())
                        .orElseThrow(() -> new EServiceLayer("El tipo de cargo no existe"));
        // Corroboramos contrato
        Contract contract_reading =
                contractRepository.findByUuid(dto.getContract_id_uuid())
                        .orElseThrow(() -> new EServiceLayer("El tipo de contrato no existe"));
        // Corroboramos distrito
        Distric distric_reading =
                districRepository.findByUuid(dto.getDistric_id_uuid())
                        .orElseThrow(() -> new EServiceLayer("El tipo de distrito no existe"));

        // Convertimos de DTO a modelo
        Employee model = mapper.toModel(dto);
        // Asignamos un UUID y un estado
        model.setUuid(UUID.randomUUID());
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build()); // ACTIVO
        // Asignamos los valores de las relaciones
        model.setIdentification_type_id(identification_type_reading);
        model.setPost_id(post_reading);
        model.setContract_id(contract_reading);
        model.setDistric_id(distric_reading);

        model = repository.save(model);

        return mapper.toDTO(model);
    }

    @Override
    public EmployeeResponseDTO readByUUID(UUID uuid) {
        log.info("EmployeeService.readByUUID()");

        Employee model = searchEntityByUUID(uuid);

        return mapper.toDTO(model);
    }

    @Override
    public void remove(UUID uuid) {
        log.info("EmployeeService.remove()");
        // Buscamos el employee por su UUID
        Employee model_existente = searchEntityByUUID(uuid);
        // Actualizar el estado del employee a eliminado
        model_existente.setState_entity_id(StateEntity.builder().state_entity_id(3).build()); // ELIMINADO
        // Guardamos los cambios
        model_existente = repository.save(model_existente);
    }

    @Override
    public List<EmployeeResponseDTO> allList() {
        log.info("EmployeeService.allList()");
        return repository.findAll().stream()
                .filter(employee -> employee.getState_entity_id().getState_entity_id()!= 3)
                .map(mapper::toDTO).toList();
    }

    @Override
    public EmployeeResponseDTO updateByUUID(UUID uuid, EmployeeRequestDTO dto) {
        log.info("EmployeeService.updateByUUID()");
        // Buscamos el employee por su UUID
        Employee model_existente = searchEntityByUUID(uuid);
        // Corroboramos todas las relaciones
        if(dto.getIdentification_type_uuid() != null) {
            identificationTypeRepository.findByUuid(dto.getIdentification_type_uuid())
                    .orElseThrow(() -> new EServiceLayer("El tipo de identificación no existe"));
        } if (dto.getPost_id_uuid() != null) {
            postRepository.findByUuid(dto.getPost_id_uuid())
                    .orElseThrow(() -> new EServiceLayer("El tipo de cargo no existe"));
        } if (dto.getContract_id_uuid() != null) {
            contractRepository.findByUuid(dto.getContract_id_uuid())
                    .orElseThrow(() -> new EServiceLayer("El tipo de contrato no existe"));
        } if (dto.getDistric_id_uuid() != null) {
            districRepository.findByUuid(dto.getDistric_id_uuid())
                    .orElseThrow(() -> new EServiceLayer("El tipo de distrito no existe"));
        }
        // Actualizamos los datos
        mapper.updateFromDto(dto, model_existente);
        // Guardamos los cambios
        model_existente = repository.save(model_existente);
        // Retornamos el DTO con los datos actualizados
        return mapper.toDTO(model_existente);
    }

    /**
     * Busca el empleado por su UUID
     * @param uuid
     * @return
     */
    private Employee searchEntityByUUID(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new EServiceLayer
                (String.format("No se encontró el empleado con el id público: %s", uuid)));
    }
}
