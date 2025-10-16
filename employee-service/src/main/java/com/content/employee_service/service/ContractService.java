package com.content.employee_service.service;

import com.content.employee_service.dto.request.ContractRequestDTO;
import com.content.employee_service.dto.response.ContractResponseDTO;
import com.content.employee_service.dto.response.ContractTypeResponseDTO;
import com.content.employee_service.exception.EServiceLayer;
import com.content.employee_service.exception.EValidation;
import com.content.employee_service.exception.ObjectErrorValidation;
import com.content.employee_service.mapper.mapperImpl.ContractMapper;
import com.content.employee_service.mapper.mapperImpl.ContractTypeMapper;
import com.content.employee_service.model.Contract;
import com.content.employee_service.model.ContractType;
import com.content.employee_service.model.StateEntity;
import com.content.employee_service.repository.ContractRepository;
import com.content.employee_service.repository.ContractTypeRepository;
import com.content.employee_service.service.abstractService.ServiceAbs;
import com.content.employee_service.utility.UtilityValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@Slf4j
@RequiredArgsConstructor
public class ContractService implements ServiceAbs<ContractRequestDTO, ContractResponseDTO> {
    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    private final ContractTypeMapper contractTypeMapper;
    private final ContractTypeRepository contractTypeRepository;

    @Override
    public ContractResponseDTO create(ContractRequestDTO dto) {
        log.info("ContractService.create()");

        // Corroboramos si existe el tipo de contrato
        ContractType contract_type_reading =
                contractTypeRepository.findByUuid(dto.getContract_type_uuid())
                        .orElseThrow(() -> new EServiceLayer("El tipo de contrato no existe"));
        // Convertimos de DTO a modelo
        Contract model = contractMapper.toModel(dto);
        // Asignamos el UUID, el estado del contrato y el tipo de contrato
        model.setUuid(UUID.randomUUID());
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build());
        model.setContract_type_id(contract_type_reading);

        Contract modelSave = contractRepository.save(model);
        return contractMapper.toDTO(modelSave);
    }

    @Override
    public List<ContractResponseDTO> allList() {
        log.info("ContractService.allList()");
        // lista menos los eliminados
        return contractRepository.findAll().stream()
                .filter(contract -> contract.getState_entity_id().getState_entity_id() != 3)
                .map(contractMapper::toDTO)
                .toList();
    }

    @Override
    public ContractResponseDTO readByUUID(UUID uuid) {
        log.info("ContractService.readByUUID()");

        Contract model = searchEntityByUUID(uuid);

        return contractMapper.toDTO(model);
    }

    @Override
    public void remove(UUID uuid) {
        log.info("ContractService.remove()");

        // Buscamos el tipo de persona por su UUID
        Contract model_existente = searchEntityByUUID(uuid);
        // Cambiamos el estado del contrato a eliminado
        model_existente.setState_entity_id(StateEntity.builder().state_entity_id(3).build());

        contractRepository.save(model_existente);
    }

    @Override
    public ContractResponseDTO updateByUUID(UUID uuid, ContractRequestDTO dto) {
        log.info("ContractService.updateByUUID()");

        // Buscamos el contrato por su UUID
        Contract model_existente = searchEntityByUUID(uuid);

        // Validamos si se va actualizar el uuid del tipo de contrato
        if (dto.getContract_type_uuid() != null) {
            ContractType new_contract_type = contractTypeRepository.findByUuid(dto.getContract_type_uuid())
                    .orElseThrow(() -> new EValidation(
                            List.of(new ObjectErrorValidation("contractTypeUuid", "El tipo de contrato no existe"))
                    ));
            model_existente.setContract_type_id(new_contract_type);
        }


        // Actualizamos los datos
        contractMapper.updateFromDto(dto, model_existente);
        /*Contract model_mapeado = contractMapper.toModel(dto);

        model_mapeado.setContract_id( model_existente.getContract_id());
        model_mapeado.setUuid(model_existente.getUuid());*/


        // Guardamos los cambios
        Contract model_actualizado = contractRepository.save(model_existente);
        // Retornamos el DTO con los datos actualizados
        return contractMapper.toDTO(model_actualizado);
    }

    private Contract searchEntityByUUID(UUID uuid) {
        return contractRepository.findByUuid(uuid).orElseThrow(() -> new EServiceLayer
                (String.format("No se encontró el contrato con el id público: %s", uuid)));
    }
}
