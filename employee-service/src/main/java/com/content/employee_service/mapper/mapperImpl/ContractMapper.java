package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.ContractRequestDTO;
import com.content.employee_service.dto.response.ContractResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.model.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper
        extends Convert<Contract, ContractRequestDTO, ContractResponseDTO> {
    @Mapping(target = "contract_type_id", source = "contract_type_id.contract_type_id")
    @Override
    ContractResponseDTO toDTO(Contract model);

    @Override
    Contract toModel(ContractRequestDTO dto);
}
