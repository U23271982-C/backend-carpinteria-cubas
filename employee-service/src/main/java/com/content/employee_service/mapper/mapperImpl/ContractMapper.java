package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.ContractRequestDTO;
import com.content.employee_service.dto.response.ContractResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.mapper.convert.UpdatePatch;
import com.content.employee_service.model.Contract;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ContractMapper
        extends Convert<Contract, ContractRequestDTO,
        ContractResponseDTO>, UpdatePatch<ContractRequestDTO, Contract> {

    @Mapping(target = "contract_name", source = "contract_name")
    @Mapping(target = "start_date", source = "start_date")
    @Mapping(target = "end_date", source = "end_date")
    @Mapping(target = "contract_type_uuid", source = "contract_type_id.uuid")
    @Mapping(target = "salary", source = "salary")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "state_entity", source = "state_entity_id.state_entity_name")
    @Override
    ContractResponseDTO toDTO(Contract model);

    @Mapping(target = "contract_name", source = "contract_name")
    @Mapping(target = "start_date", source = "start_date")
    @Mapping(target = "end_date", source = "end_date")
    @Mapping(target = "salary", source = "salary")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    Contract toModel(ContractRequestDTO dto);
}
