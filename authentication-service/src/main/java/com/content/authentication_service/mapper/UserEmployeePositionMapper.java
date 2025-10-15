package com.content.authentication_service.mapper;
import com.content.authentication_service.dto.request.UserEmployeePositionRequestDTO;
import com.content.authentication_service.mapper.convert.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEmployeePositionMapper extends Convert<UserEmployeePosition, UserEmployeePositionRequestDTO, UserEmployeePositionRequestDTO > {

    U

}
