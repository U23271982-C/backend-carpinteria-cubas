package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.ModuleRequestDTO;
import com.content.authentication_service.dto.response.ModuleResponseDTO;
import com.content.authentication_service.mapper.ModuleMapper;
import com.content.authentication_service.model.Module;
import com.content.authentication_service.model.StateEntity;
import com.content.authentication_service.repository.ModuleRepository;
import com.content.authentication_service.repository.StateEntityRepository;
import com.content.authentication_service.service.abstractservice.ServiceAbs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ModuleServiceImpl implements ServiceAbs<ModuleRequestDTO, ModuleResponseDTO> {

    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;
    private final StateEntityServiceImpl stateEntityServiceImpl;

    @Override
    public ModuleResponseDTO create(ModuleRequestDTO dto) {
        Module module = moduleMapper.toModel(dto);

        UUID uuid = UUID.randomUUID();
        module.setUuid(uuid);

        if(module.getState_entity_id().getUuid() == null){
            log.info("No se proporcionó state_entity_id, asignando estado predeterminado (activo)");
            module.setState_entity_id(stateEntityServiceImpl.getStateActive());
        }
        Module savedModule = moduleRepository.save(module);
        return moduleMapper.toDTO(savedModule);
    }

    @Override
    public List<ModuleResponseDTO> allList() {
        return moduleRepository.findAll()
                .stream()
                .filter(module -> module.getState_entity_id().getState_entity_id() != 3)
                .map(moduleMapper::toDTO)
                .toList(); // Excluir eliminados;
    }

    @Override
    public ModuleResponseDTO readById(UUID uuid) {
        return moduleMapper.toDTO(findByUUID(uuid));
    }

    @Override
    public void remove(UUID uuid) {
        Module module = findByUUID(uuid);
        module.setState_entity_id(stateEntityServiceImpl.deleteEntity());
        moduleRepository.save(module);
    }

    @Override
    public ModuleResponseDTO update(UUID uuid, ModuleRequestDTO dto) {
        Module existingModule = findByUUID(uuid);

        if (dto.getModule_name() != null && !dto.getModule_name().trim().isEmpty()) {
            existingModule.setModule_name(dto.getModule_name());
        }
        if (dto.getModule_description() != null && !dto.getModule_description().trim().isEmpty()) {
            existingModule.setModule_description(dto.getModule_description());
        }

        if (dto.getStateEntityuuid() != null) {
            StateEntity stateEntityNew = stateEntityServiceImpl.getByUUID(dto.getStateEntityuuid());
            existingModule.setState_entity_id(stateEntityNew);
        }
        Module updatedModule = moduleRepository.save(existingModule);
        return moduleMapper.toDTO(updatedModule);
    }


    public Module findByUUID(UUID uuid) {
        return moduleRepository.findAll()
                .stream()
                .filter( mod -> mod.getUuid().equals(uuid) && mod.getState_entity_id().getState_entity_id() != 3)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro el module con uuid: " + uuid));
    }

    public Module findByUUIDActive(UUID uuid) {
        return moduleRepository.findAll()
                .stream()
                .filter( mod -> mod.getUuid().equals(uuid) && mod.getState_entity_id().getState_entity_id() == 1)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro el module activo con uuid: " + uuid));
    }
}
