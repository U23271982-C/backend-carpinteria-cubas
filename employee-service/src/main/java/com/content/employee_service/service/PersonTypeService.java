package com.content.employee_service.service;

import com.content.employee_service.dto.request.PersonTypeRequestDTO;
import com.content.employee_service.dto.response.PersonTypeResponseDTO;
import com.content.employee_service.exception.EServiceLayer;
import com.content.employee_service.mapper.mapperImpl.PersonTypeMapper;
import com.content.employee_service.model.PersonType;
import com.content.employee_service.model.StateEntity;
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
public class PersonTypeService implements ServiceAbs<PersonTypeRequestDTO, PersonTypeResponseDTO> {
    private final PersonTypeRepository repository;
    private final PersonTypeMapper personTypeMapper;

    @Override
    public PersonTypeResponseDTO create(PersonTypeRequestDTO dto) {
        log.info("PersonTypeService.create()");
        PersonType model = personTypeMapper.toModel(dto);

        model.setUuid(UUID.randomUUID());
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build());

        PersonType modelSave = repository.save(model);
        return personTypeMapper.toDTO(modelSave);
    }

    @Override
    public List<PersonTypeResponseDTO> allList() {
        log.info("PersonTypeService.allList()");

        return repository.findAll().stream().map(personTypeMapper::toDTO).toList();
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
        repository.delete(model_existente);
    }

    @Override
    public PersonTypeResponseDTO updateByUUID(UUID uuid, PersonTypeRequestDTO dto) {
        log.info("PersonTypeService.updateByUUID()");

        // Buscamos el tipo de persona por su UUID
         PersonType model_existente = searchEntityByUUID(uuid);
        // Actualizamos los datos
        model_existente.setPerson_type_name(dto.getPerson_type_name());

        // Guardamos los cambios
        PersonType model_actualizado = repository.save(model_existente);

        return personTypeMapper.toDTO(model_actualizado);
    }

    private PersonType searchEntityByUUID(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new EServiceLayer
                (String.format("No se encontró el tipo de persona con el id público: %s", uuid)));
    }
}
