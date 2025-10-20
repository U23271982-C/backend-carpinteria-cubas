package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.ContractTypeRequestDTO;
import com.content.employee_service.dto.response.ContractTypeResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.mapper.convert.UpdatePatch;
import com.content.employee_service.model.ContractType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractTypeMapper
        extends Convert<ContractType, ContractTypeRequestDTO, ContractTypeResponseDTO>,
        UpdatePatch<ContractTypeRequestDTO, ContractType> {

    @Mapping(target = "contract_type_name", source = "contract_type_name")
    @Mapping(target = "description", source = "descripcion")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "state_entity", source = "state_entity_id.state_entity_name")
    @Override
    ContractTypeResponseDTO toDTO(ContractType model);

    @Mapping(target = "contract_type_name", source = "contract_type_name")
    @Mapping(target = "descripcion", source = "description")
    @Mapping(target = "contract_type_id", ignore = true)
    @Mapping(target = "state_entity_id", ignore = true)
    @Mapping(target = "contracts", ignore = true)
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    ContractType toModel(ContractTypeRequestDTO dto);
}
