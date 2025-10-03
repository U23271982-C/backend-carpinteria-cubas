package com.content.inventory_matter_service.service.abstractService;

import java.util.List;

public interface Listable <D>{
    List<D> list(Long id);
}
