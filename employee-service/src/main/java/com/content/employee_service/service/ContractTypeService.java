package com.content.employee_service.service;

import com.content.employee_service.dto.request.ContractTypeRequestDTO;
import com.content.employee_service.dto.response.ContractTypeResponseDTO;
import com.content.employee_service.exception.EServiceLayer;
import com.content.employee_service.mapper.mapperImpl.ContractTypeMapper;
import com.content.employee_service.model.ContractType;
import com.content.employee_service.model.StateEntity;
import com.content.employee_service.repository.ContractTypeRepository;
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
public class ContractTypeService implements ServiceAbs<ContractTypeRequestDTO, ContractTypeResponseDTO> {
    private final ContractTypeRepository contractTypeRepository;
    private final ContractTypeMapper contractTypeMapper;

    private final StateEntityRepository stateEntityRepository;
    @Override
    public ContractTypeResponseDTO create(ContractTypeRequestDTO dto) {
        log.info("ContractTypeService.create()");

        ContractType model = contractTypeMapper.toModel(dto);
        model.setUuid(UUID.randomUUID());
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build());

        ContractType modelSave = contractTypeRepository.save(model);
        return contractTypeMapper.toDTO(modelSave);
    }

    @Override
    public List<ContractTypeResponseDTO> allList() {
        log.info("ContractTypeService.allList()");
        // lista menos los eliminados
        return contractTypeRepository.findAll().stream()
                .filter(contractType -> contractType.getState_entity_id().getState_entity_id() != 3)
                .map(contractTypeMapper::toDTO)
                .toList();
    }

    @Override
    public ContractTypeResponseDTO readByUUID(UUID uuid) {
        log.info("ContractTypeService.readByUUID()");

        ContractType model = searchEntityByUUID(uuid);

        return contractTypeMapper.toDTO(model);
    }

    @Override
    public void remove(UUID uuid) {
        log.info("ContractTypeService.remove()");
        // Buscamos el tipo de contrato por su UUID
        ContractType model_existente = searchEntityByUUID(uuid);
        // Cambiamos el estado del tipo de contrato a eliminado
        model_existente.setState_entity_id(StateEntity.builder().state_entity_id(3).build());

        contractTypeRepository.save(model_existente);
    }

    @Override
    public ContractTypeResponseDTO updateByUUID(UUID uuid, ContractTypeRequestDTO dto) {
        log.info("ContractTypeService.updateByUUID()");
        // Buscamos el contrato por su UUID
        ContractType model_existente = searchEntityByUUID(uuid);
        // Actualizamos el estado del tipo de persona si se requiere
        if (dto.getState_entity_uuid() != null) {
            StateEntity state_entity_exiting = stateEntityRepository.findByUuid(dto.getState_entity_uuid())
                    .orElseThrow(() -> new EServiceLayer("El estado de entidad no existe"));

            model_existente.setState_entity_id(StateEntity.builder()
                    // Agregamos el nuevo id del estado, para que se pueda asociar con FK
                    .state_entity_id(state_entity_exiting.getState_entity_id()).build());
        }
        // Actualizamos los datos
        contractTypeMapper.updateFromDto(dto, model_existente);

        // Guardamos los cambios
        ContractType model_guardado = contractTypeRepository.save(model_existente);
        // Retornamos el DTO con los datos actualizados
        return contractTypeMapper.toDTO(model_guardado);
    }

    /**
     * Busca el tipo de contrato por su UUID
     * @param uuid
     * @return
     */
    private ContractType searchEntityByUUID(UUID uuid) {
        return contractTypeRepository.findByUuid(uuid)
                .filter(entity -> entity.getState_entity_id().getState_entity_id()!= 3)
                .orElseThrow(
                        () -> {
                            if (contractTypeRepository.findByUuid(uuid).isPresent()) {
                                // El empleado existe, pero fue filtrado (estado == 3)
                                throw new EServiceLayer("El tipo de contrato está eliminado");
                            } else {
                                // El empleado nunca fue encontrado
                                return new EServiceLayer(
                                        String.format("No se encontró el tipo de contrato con el id público: %s", uuid)
                                );
                            }
                        }
                );
    }
}
