package com.content.trabajador_servicio.dto;

import com.content.trabajador_servicio.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressTypeDTO {

    private Integer id;

    private String nameAddressType;

    private String description;

    private List<Address> addresses;

    private Integer id_State;
}
