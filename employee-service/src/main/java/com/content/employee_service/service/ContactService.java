package com.content.employee_service.service;

import com.content.employee_service.dto.request.ContactRequestDTO;
import com.content.employee_service.dto.request.ContractRequestDTO;
import com.content.employee_service.dto.response.ContactResponseDTO;
import com.content.employee_service.exception.EServiceLayer;
import com.content.employee_service.mapper.mapperImpl.ContactMapper;
import com.content.employee_service.model.Contact;
import com.content.employee_service.model.Employee;
import com.content.employee_service.model.StateEntity;
import com.content.employee_service.repository.ContactRepository;
import com.content.employee_service.repository.EmployeeRepository;
import com.content.employee_service.service.abstractService.ServiceAbs;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@Slf4j
@AllArgsConstructor
public class ContactService implements ServiceAbs<ContactRequestDTO, ContactResponseDTO> {
    private final ContactMapper mapper;
    private final ContactRepository repository;

    private final EmployeeRepository employeeRepository;

    @Override
    public ContactResponseDTO create(ContactRequestDTO dto) {
        log.info("ContactService.create()");
        // Validamos las relaciones entre los datos del DTO
        Employee employee_reading = employeeRepository.findByUuid(dto.getEmployee_id_uuid())
                .orElseThrow(() -> new EServiceLayer("El empleado no existe"));
        // Convertimos de DTO a modelo
        Contact model = mapper.toModel(dto);
        // Asignamos un UUID y un estado
        model.setUuid(UUID.randomUUID());
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build()); // ACTIVO
        model.setEmployee_id(employee_reading);

        model = repository.save(model);
        return mapper.toDTO(model);
    }

    @Override
    public List<ContactResponseDTO> allList() {
        log.info("ContactService.allList()");

        return repository.findAll().stream()
                .filter(contact -> contact.getState_entity_id().getState_entity_id() != 3)
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public ContactResponseDTO readByUUID(UUID uuid) {
        log.info("ContactService.readByUUID()");
        // Buscamos el contacto por su UUID
        Contact model = searchEntityByUUID(uuid);

        return mapper.toDTO(model);
    }

    @Override
    public void remove(UUID uuid) {
        log.info("ContactService.remove()");
        // Buscamos el contacto por su UUID
        Contact model_existente = searchEntityByUUID(uuid);
        // Cambiamos el estado del contacto a eliminado
        model_existente.setState_entity_id(StateEntity.builder().state_entity_id(3).build());
        // Guardamos el modelo en la BD
        repository.save(model_existente);
    }

    @Override
    public ContactResponseDTO updateByUUID(UUID uuid, ContactRequestDTO dto) {
        log.info("ContactService.updateByUUID()");
        // Buscamos el contacto por su UUID
        Contact model_existente = searchEntityByUUID(uuid);
        // Corroboramos si existe el empleado
        if (dto.getEmployee_id_uuid() != null) {
            Employee employee_reading = employeeRepository.findByUuid(dto.getEmployee_id_uuid())
                    .orElseThrow(() -> new EServiceLayer("El empleado no existe"));
            model_existente.setEmployee_id(employee_reading);
        }
        // Actualizamos los datos
        mapper.updateFromDto(dto, model_existente);
        // Guardamos los cambios
        model_existente = repository.save(model_existente);
        // Retornamos el DTO con los datos actualizados
        return mapper.toDTO(model_existente);
    }

    /**
     * Busca el contacto por su UUID
     * @param uuid
     * @return contacto existente
     */
    private Contact searchEntityByUUID(UUID uuid){
        return repository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("El contacto no existe"));
    }

    /**
     * Valida las relaciones entre los datos del DTO
     * y las setea en el dto
     * @param dto
     * @return dto con los UUID seteados
     */
    private void validateRelations(ContactRequestDTO dto){
        employeeRepository.findByUuid(dto.getEmployee_id_uuid())
                .orElseThrow(() -> new EServiceLayer("El contacto no existe"));
    }
}
