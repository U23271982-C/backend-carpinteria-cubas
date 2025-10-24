package com.content.employee_service.service;

import com.content.employee_service.dto.request.PersonTypeRequestDTO;
import com.content.employee_service.dto.response.PersonTypeResponseDTO;
import com.content.employee_service.exception.EServiceLayer;
import com.content.employee_service.mapper.mapperImpl.PersonTypeMapper;
import com.content.employee_service.model.PersonType;
import com.content.employee_service.model.StateEntity;
import com.content.employee_service.repository.PersonTypeRepository;
import com.content.employee_service.repository.StateEntityRepository;
import com.content.employee_service.service.abstractService.ServiceAbs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonTypeService implements ServiceAbs<PersonTypeRequestDTO, PersonTypeResponseDTO> {
    private final PersonTypeRepository repository;
    private final PersonTypeMapper personTypeMapper;

    private final StateEntityRepository stateEntityRepository;
    @Override
    public PersonTypeResponseDTO create(PersonTypeRequestDTO dto) {
        log.info("PersonTypeService.create()");
        PersonType model = personTypeMapper.toModel(dto);

        model.setUuid(UUID.randomUUID());
        // Asignamos el estado activo (ID = 1)
        StateEntity state_entity_reading = stateEntityRepository.findById(1)
                .orElseThrow(() -> new EServiceLayer("El estado no existe"));

        model.setState_entity_id(state_entity_reading);

        PersonType modelSave = repository.save(model);
        return personTypeMapper.toDTO(modelSave);
    }

    @Override
    public List<PersonTypeResponseDTO> allList() {
        log.info("PersonTypeService.allList()");
        // lista menos los eliminados
        return repository.findAll().stream()
                .filter(personType -> personType.getState_entity_id().getState_entity_id() != 3)
                .map(personTypeMapper::toDTO).toList();
    }

    @Override
    public PersonTypeResponseDTO readByUUID(UUID uuid) {
        log.info("PersonTypeService.readById()");

        PersonType model = searchEntityByUUID(uuid);

        return personTypeMapper.toDTO(model);
    }

    @Override
    public void remove(UUID uuid) {
        log.info("PersonTypeService.remove()");

        // Buscamos el tipo de persona por su UUID
        PersonType model_existente = searchEntityByUUID(uuid);
        // Cambiamos el estado del tipo de persona a eliminado
        model_existente.setState_entity_id(StateEntity.builder().state_entity_id(3).build());

        repository.save(model_existente);
    }

    @Override
    public PersonTypeResponseDTO updateByUUID(UUID uuid, PersonTypeRequestDTO dto) {
        log.info("PersonTypeService.updateByUUID()");

        // Buscamos el tipo de persona por su UUID
         PersonType model_existente = searchEntityByUUID(uuid);
        /*
        model_existente.setPerson_type_name(dto.getPerson_type_name());*/

        // Actualizamos el estado del tipo de persona
        if (dto.getState_entity_uuid() != null) {
            StateEntity state_entity_exiting = stateEntityRepository.findByUuid(dto.getState_entity_uuid())
                    .orElseThrow(() -> new EServiceLayer("El estado de entidad no existe"));

            model_existente.setState_entity_id(state_entity_exiting);
        }

        // Actualizamos los datos
        personTypeMapper.updateFromDto(dto, model_existente);
        // Guardamos los cambios
        PersonType model_actualizado = repository.save(model_existente);

        return personTypeMapper.toDTO(model_actualizado);
    }

    /**
     * Busca el tipo de persona por su UUID
     * @param uuid
     * @return
     */
    private PersonType searchEntityByUUID(UUID uuid) {
        return repository.findByUuid(uuid)
                .filter(entity -> entity.getState_entity_id().getState_entity_id()!= 3)
                .orElseThrow(
                        () -> {
                            if (repository.findByUuid(uuid).isPresent()) {
                                // El empleado existe, pero fue filtrado (estado == 3)
                                throw new EServiceLayer("El tipo de persona está eliminado");
                            } else {
                                // El empleado nunca fue encontrado
                                return new EServiceLayer(
                                        String.format("No se encontró el tipo de persona con el id público: %s", uuid)
                                );
                            }
                        }
                );
    }
}
