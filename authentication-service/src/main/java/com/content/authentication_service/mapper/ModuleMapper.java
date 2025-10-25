package com.content.authentication_service.mapper;
import com.content.authentication_service.dto.request.ModuleRequestDTO;
import com.content.authentication_service.dto.response.ModuleResponseDTO;
import com.content.authentication_service.mapper.convert.Convert;
import com.content.authentication_service.model.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModuleMapper extends Convert<Module, ModuleRequestDTO, ModuleResponseDTO> {

    ModuleMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(ModuleMapper.class);

    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "name", target = "module_name")
    @Mapping(source = "module_description", target = "module_description")
    @Mapping(source = "state_entity_id.state_entity_name", target = "state_entity_name")
    @Override
    ModuleResponseDTO toDTO(Module modelo);


    @Mapping(target = "module_id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "name", source = "module_name")
    @Mapping(target = "module_description", source = "module_description")
    @Mapping(target = "state_entity_id.uuid", source = "stateEntityuuid") // Asignamos el UUID del estado directamente
    @Override
    Module toModel(ModuleRequestDTO dto);

}
