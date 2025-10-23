package com.content.employee_service.service;

import com.content.employee_service.dto.request.IdentificationTypeRequestDTO;
import com.content.employee_service.dto.response.IdentificationTypeResponseDTO;
import com.content.employee_service.exception.EServiceLayer;
import com.content.employee_service.mapper.mapperImpl.IdentificationTypeMapper;
import com.content.employee_service.model.Contract;
import com.content.employee_service.model.IdentificationType;
import com.content.employee_service.model.PersonType;
import com.content.employee_service.model.StateEntity;
import com.content.employee_service.repository.IdentificationTypeRepository;
import com.content.employee_service.repository.PersonTypeRepository;
import com.content.employee_service.service.abstractService.ServiceAbs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@Slf4j
@RequiredArgsConstructor
public class IdentificationTypeService implements
        ServiceAbs<IdentificationTypeRequestDTO, IdentificationTypeResponseDTO> {

    private final IdentificationTypeRepository repository;
    private final IdentificationTypeMapper mapper;

    private final PersonTypeRepository personTypeRepository;

    @Override
    public IdentificationTypeResponseDTO create(IdentificationTypeRequestDTO dto) {
        log.info("IdentificationTypeService.create()");

        // Corroboramos si el tipo de persona existe
        PersonType person_type_reading =
                personTypeRepository.findByUuid(dto.getPerson_type_uuid())
                        .orElseThrow(() -> new EServiceLayer("El tipo de persona no existe"));
        // Convertimos de DTO a modelo
        IdentificationType model = mapper.toModel(dto);
        // Asignamos el UUID y el tipo de persona
        model.setUuid(UUID.randomUUID());
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build());
        model.setPerson_type_id(person_type_reading);

        IdentificationType modelSave = repository.save(model);
        return mapper.toDTO(modelSave);
    }

    @Override
    public List<IdentificationTypeResponseDTO> allList() {
        log.info("IdentificationTypeService.allList()");
        return repository.findAll()
                .stream()
                .filter(identificationType -> identificationType.getState_entity_id().getState_entity_id() != 3)
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public IdentificationTypeResponseDTO readByUUID(UUID uuid) {
        log.info("IdentificationTypeService.readByUUID()");

        IdentificationType model = searchEntityByUUID(uuid);

        return mapper.toDTO(model);
    }

    @Override
    public void remove(UUID uuid) {
        log.info("IdentificationTypeService.remove()");

        IdentificationType model_existente = searchEntityByUUID(uuid);
        // Cambiamos el estado del tipo de identificacion a eliminado
        model_existente.setState_entity_id(StateEntity.builder().state_entity_id(3).build());

        repository.save(model_existente);
    }

    @Override
    public IdentificationTypeResponseDTO updateByUUID(UUID uuid, IdentificationTypeRequestDTO dto) {
        log.info("IdentificationTypeService.updateByUUID()");

        IdentificationType model_existente = searchEntityByUUID(uuid);
        // Corroboramos todas las relaciones
        if(dto.getPerson_type_uuid() != null) {
            PersonType person_type_reading = personTypeRepository.findByUuid(dto.getPerson_type_uuid())
                    .orElseThrow(() -> new EServiceLayer("El tipo de persona no existe"));
            model_existente.setPerson_type_id(person_type_reading);
        }
        // Actualizamos los datos
        mapper.updateFromDto(dto, model_existente);
        // Guardamos los cambios
        model_existente = repository.save(model_existente);

        return mapper.toDTO(model_existente);
    }

    /**
     * Busca tipo de indentificacion por su UUID
     * @param uuid
     * @return
     */
    private IdentificationType searchEntityByUUID(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new EServiceLayer
                (String.format("No se encontró el tipo de identificación con el id público: %s", uuid)));
    }
}
