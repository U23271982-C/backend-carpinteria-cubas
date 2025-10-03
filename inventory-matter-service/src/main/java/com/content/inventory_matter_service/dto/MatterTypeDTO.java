package com.content.inventory_matter_service.dto;

import com.content.inventory_matter_service.model.Matter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MatterTypeDTO {

    private Integer id;

    private String mattertype_name;

    private List<Matter> Matter;

    private Integer State_id;

}
