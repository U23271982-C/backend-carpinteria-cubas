package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.ContractTypeRequestDTO;
import com.content.employee_service.dto.response.ContractResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.model.ContractType;

public interface ContractTypeMapper
        extends Convert<ContractType, ContractTypeRequestDTO, ContractResponseDTO> {

    @Override
    ContractResponseDTO toDTO(ContractType model);

    @Override
    ContractType toModel(ContractTypeRequestDTO dto);
}
