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

    @Mapping(target = "contract_name", source = "contract_name")
    @Mapping(target = "start_date", source = "start_date")
    @Mapping(target = "end_date", source = "end_date")
    @Mapping(target = "contract_type_id", source = "contract_type_id.contract_type_id")
    @Mapping(target = "salary", source = "salary")
    @Mapping(target = "uuid", source = "uuid")
    @Override
    ContractResponseDTO toDTO(Contract model);

    @Mapping(target = "contract_name", source = "contract_name")
    @Mapping(target = "start_date", source = "start_date")
    @Mapping(target = "end_date", source = "end_date")
    @Mapping(target = "salary", source = "salary")
    @Override
    Contract toModel(ContractRequestDTO dto);
}
