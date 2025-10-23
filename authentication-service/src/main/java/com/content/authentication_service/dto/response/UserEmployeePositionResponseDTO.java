package com.content.authentication_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserEmployeePositionResponseDTO {

    private String uuid;
    private String positionName;
    private String positionDescription;
    private String nameStateEntity;

}
