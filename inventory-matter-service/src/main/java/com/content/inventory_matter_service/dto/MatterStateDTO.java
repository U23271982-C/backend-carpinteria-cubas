package com.content.inventory_matter_service.dto;

import com.content.inventory_matter_service.model.Matter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MatterStateDTO {

    private Integer id;

    private String matterState_name;

    private Integer State_id;

    private List<Matter> Matters;
}
