package com.content.customer_service.service;

import com.content.customer_service.dto.request.IdentificationRequestDTO;
import com.content.customer_service.dto.response.IdentificationResponseDTO;
import com.content.customer_service.exception.EServiceLayer;
import com.content.customer_service.exception.EValidation;
import com.content.customer_service.exception.ObjectErrorValidation;
import com.content.customer_service.mapper.mapperImpl.IdentificationMapper;
import com.content.customer_service.model.Identification;
import com.content.customer_service.model.IdentificationType;
import com.content.customer_service.model.StateEntity;
import com.content.customer_service.repository.IdentificationRepository;
import com.content.customer_service.repository.IdentificationTypeRepository;
import com.content.customer_service.service.abstractService.ServiceAbs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class IdentificationService implements ServiceAbs<IdentificationRequestDTO, IdentificationResponseDTO> {

    private final IdentificationRepository identificationRepository;
    private final IdentificationMapper identificationMapper;
    private final IdentificationTypeRepository identificationTypeRepository;

    @Override
    public IdentificationResponseDTO create(IdentificationRequestDTO dto) {
        log.info("IdentificationService.create()");
        IdentificationType identificationType_reading =
                identificationTypeRepository.findByUuid(dto.getIdentification_type_uuid())
                        .orElseThrow(() -> new EServiceLayer("El tipo de identificacion no existe"));
        Identification model = identificationMapper.toModel(dto);
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build());
        model.setIdentification_type_id(identificationType_reading);
        model = identificationRepository.save(model);
        return identificationMapper.toDTO(model);
    }

    @Override
    public List<IdentificationResponseDTO> allList() {
        log.info("IdentificationService.allList()");
        return identificationRepository.findAll().stream()
                .filter(identification -> identification.getState_entity_id().getState_entity_id() != 3)
                .map(identificationMapper::toDTO)
                .toList();
    }

    @Override
    public IdentificationResponseDTO readByUUID(UUID uuid) {
        log.info("IdentificationService.readByUUID()");
        Identification model = searchEntityByUUID(uuid);
        return identificationMapper.toDTO(model);
    }

    @Override
    public void remove(UUID uuid) {
        log.info("IdentificationService.remove()");
        Identification model = searchEntityByUUID(uuid);
        model.setState_entity_id(StateEntity.builder().state_entity_id(3).build());
        identificationRepository.save(model);
    }

    @Override
    public IdentificationResponseDTO updateByUUID(UUID uuid, IdentificationRequestDTO dto) {
        log.info("IdentificationService.updateByUUID()");
        Identification model_exiting = searchEntityByUUID(uuid);
        if (dto.getIdentification_type_uuid() != null) {
            IdentificationType identificationType_reading =
                    identificationTypeRepository.findByUuid(dto.getIdentification_type_uuid())
                            .orElseThrow(() -> new EValidation(List.of(new ObjectErrorValidation("identification_type_uuid", "El tipo de identificacion no existe"))
                            ));
            model_exiting.setIdentification_type_id(identificationType_reading);
        }
        identificationMapper.updateFromDto(dto, model_exiting);
        Identification modelUpdated = identificationRepository.save(model_exiting);
        return identificationMapper.toDTO(modelUpdated);
    }

    private Identification searchEntityByUUID(UUID uuid) {
        log.info("IdentificationService.searchEntityByUUID()");
        return identificationRepository.findByUuid(uuid)
                .orElseThrow(() -> new EServiceLayer("La identificacion no existe"));
    }
}
