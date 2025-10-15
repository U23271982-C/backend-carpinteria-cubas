package com.content.authentication_service.service.abstractservice;

import java.util.UUID;

public interface Readable<DRE>{
    DRE readById(UUID uuid);
}
