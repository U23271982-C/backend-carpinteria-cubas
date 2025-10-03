package com.content.inventory_matter_service.mapper.convert;

public interface ConvertDTO <M,D>{
    D toDTO(M model);
}
