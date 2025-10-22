package com.content.customer_service.service;

import com.content.customer_service.dto.request.PersonTypeRequestDTO;
import com.content.customer_service.dto.response.PersonTypeResponseDTO;
import com.content.customer_service.exception.EServiceLayer;
import com.content.customer_service.mapper.mapperImpl.PersonTypeMapper;
import com.content.customer_service.model.PersonType;
import com.content.customer_service.model.StateEntity;
import com.content.customer_service.repository.PersonTypeRepository;
import com.content.customer_service.service.abstractService.ServiceAbs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonTypeService implements ServiceAbs<PersonTypeRequestDTO, PersonTypeResponseDTO> {

    private final PersonTypeRepository personTypeRepository;
    private final PersonTypeMapper personTypeMapper;

    @Override
    public PersonTypeResponseDTO create(PersonTypeRequestDTO dto) {
        log.info("PersonTypeService.create()");
        PersonType model = personTypeMapper.toModel(dto);
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build());
        model = personTypeRepository.save(model);
        return personTypeMapper.toDTO(model);
    }

    @Override
    public List<PersonTypeResponseDTO> allList() {
        log.info("PersonTypeService.allList()");
        return personTypeRepository.findAll().stream()
                .filter(personType -> personType.getState_entity_id().getState_entity_id() != 3)
                .map(personTypeMapper::toDTO)
                .toList();
    }

    @Override
    public PersonTypeResponseDTO readByUUID(UUID uuid) {
        log.info("PersonTypeService.readByUUID()");
        PersonType model = searchEntityByUUID(uuid);
        return personTypeMapper.toDTO(model);
    }

    @Override
    public void remove(UUID uuid) {
        log.info("PersonTypeService.remove()");
        PersonType model_existente = searchEntityByUUID(uuid);
        model_existente.setState_entity_id(StateEntity.builder().state_entity_id(3).build());
        personTypeRepository.save(model_existente);
    }

    @Override
    public PersonTypeResponseDTO updateByUUID(UUID uuid, PersonTypeRequestDTO dto) {
        log.info("PersonTypeService.updateByUUID()");
        PersonType model_existente = searchEntityByUUID(uuid);
        personTypeMapper.updateFromDto(dto, model_existente);
        PersonType model_save = personTypeRepository.save(model_existente);
        return personTypeMapper.toDTO(model_save);
    }

    private PersonType searchEntityByUUID(UUID uuid) {
        log.info("PersonTypeService.searchEntityByUUID()");
        return personTypeRepository.findByUuid(uuid).orElseThrow(() -> new EServiceLayer
                (String.format("Tipo de persona no encontrado con UUID: " + uuid)));
    }
}
