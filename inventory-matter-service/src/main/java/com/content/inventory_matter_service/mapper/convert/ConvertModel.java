package com.content.inventory_matter_service.mapper.convert;

public interface ConvertModel <M,D>{
    M toModel(D dto);
}
