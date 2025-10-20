package com.content.authentication_service.dto.response;

import com.content.authentication_service.model.UserModuleAccess;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SessionResponseDTO {

    private String user_employee_name;
    private String user_position;
    private String user_phone;
    private String session_date;
    private List<UserModuleAccessResponseDTO> user_module_access;
    private Boolean succesed;

}
