package com.content.customer_service.service;

import com.content.customer_service.dto.request.IdentificationTypeRequestDTO;
import com.content.customer_service.dto.response.IdentificationTypeResponseDTO;
import com.content.customer_service.exception.EValidation;
import com.content.customer_service.exception.ObjectErrorValidation;
import com.content.customer_service.mapper.mapperImpl.IdentificationTypeMapper;
import com.content.customer_service.model.IdentificationType;
import com.content.customer_service.model.PersonType;
import com.content.customer_service.model.StateEntity;
import com.content.customer_service.repository.IdentificationTypeRepository;
import com.content.customer_service.repository.PersonTypeRepository;
import com.content.customer_service.service.abstractService.ServiceAbs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class IdentificationTypeService implements ServiceAbs<IdentificationTypeRequestDTO, IdentificationTypeResponseDTO> {

    private final IdentificationTypeRepository identificationTypeRepository;
    private final IdentificationTypeMapper identificationTypeMapper;
    private final PersonTypeRepository personTypeRepository;

    @Transactional
    @Override
    public IdentificationTypeResponseDTO create(IdentificationTypeRequestDTO dto) {
        log.info("IdentificationTypeService.create()");
        PersonType personType_reading =
                personTypeRepository.findByUuid(dto.getPerson_type_uuid())
                        .orElseThrow(() -> new RuntimeException("El tipo de persona no existe"));
        // Convertimos de DTO a modelo
        IdentificationType model = identificationTypeMapper.toModel(dto);
        // Asignamos el UUID, el estado del tipo de identificacion y el tipo de persona
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build());
        model.setPerson_type_id(personType_reading);
        model = identificationTypeRepository.save(model);
        return identificationTypeMapper.toDTO(model);
    }

    @Transactional
    @Override
    public List<IdentificationTypeResponseDTO> allList() {
        log.info("IdentificationTypeService.allList()");
        return identificationTypeRepository.findAll().stream()
                .filter(identificationType -> identificationType.getState_entity_id().getState_entity_id() != 3)
                .map(identificationTypeMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public IdentificationTypeResponseDTO readByUUID(UUID uuid) {
        log.info("IdentificationTypeService.readByUUID()");
        IdentificationType model = searchEntityByUUID(uuid);
        return identificationTypeMapper.toDTO(model);
    }

    @Transactional
    @Override
    public void remove(UUID uuid) {
        log.info("IdentificationTypeService.remove()");
        IdentificationType model_exiting= searchEntityByUUID(uuid);
        model_exiting.setState_entity_id(StateEntity.builder().state_entity_id(3).build());
        identificationTypeRepository.save(model_exiting);
    }

    @Transactional
    @Override
    public IdentificationTypeResponseDTO updateByUUID(UUID uuid, IdentificationTypeRequestDTO dto) {
        log.info("IdentificationTypeService.updateByUUID()");
        IdentificationType model_exiting = searchEntityByUUID(uuid);
        if (dto.getPerson_type_uuid() != null) {
            PersonType personType_reading =
                    personTypeRepository.findByUuid(dto.getPerson_type_uuid())
                            .orElseThrow(() -> new EValidation(List.of(new ObjectErrorValidation("person_type_uuid", "El tipo de persona no existe")))
                            );
            model_exiting.setPerson_type_id(personType_reading);
        }
        identificationTypeMapper.updateFromDto(dto, model_exiting);
        IdentificationType modelUpdate = identificationTypeRepository.save(model_exiting);
        return identificationTypeMapper.toDTO(modelUpdate);
    }

    private IdentificationType searchEntityByUUID(UUID uuid) {
        log.info("IdentificationTypeService.searchEntityByUUID()");
        return identificationTypeRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("El tipo de identificacion no existe"));
    }
}
