package com.content.authentication_service.service.abstractservice;

public interface Readable<DRE>{
    DRE readById(Long id);
}
