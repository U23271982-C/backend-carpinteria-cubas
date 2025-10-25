package com.content.employee_service.service;

import com.content.employee_service.dto.request.DistrictRequestDTO;
import com.content.employee_service.dto.response.DistrictResponseDTO;
import com.content.employee_service.exception.EServiceLayer;
import com.content.employee_service.mapper.mapperImpl.DistrictMapper;
import com.content.employee_service.model.ContractType;
import com.content.employee_service.model.Distric;
import com.content.employee_service.model.StateEntity;
import com.content.employee_service.repository.DistricRepository;
import com.content.employee_service.repository.StateEntityRepository;
import com.content.employee_service.service.abstractService.ServiceAbs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DistricService implements ServiceAbs<DistrictRequestDTO, DistrictResponseDTO> {
    private final DistricRepository districRepository;
    private final DistrictMapper districtMapper;

    private final StateEntityRepository stateEntityRepository;
    @Override
    public DistrictResponseDTO create(DistrictRequestDTO dto) {
        log.info("DistricService.create()");

        Distric model = districtMapper.toModel(dto);
        model.setUuid(UUID.randomUUID());
        // Asignamos el estado activo (ID = 1)
        StateEntity state_entity_reading = stateEntityRepository.findById(1)
                .orElseThrow(() -> new EServiceLayer("El estado no existe"));

        model.setState_entity_id(state_entity_reading);

        Distric modelSave = districRepository.save(model);
        return districtMapper.toDTO(model);
    }

    @Override
    public List<DistrictResponseDTO> allList() {
        log.info("DistricService.allList()");
        // lista menos los eliminados
        return districRepository.findAll()
                .stream()
                .filter(distric -> distric.getState_entity_id().getState_entity_id() != 3)
                .map(districtMapper::toDTO)
                .toList();
    }

    @Override
    public DistrictResponseDTO readByUUID(UUID uuid) {
        log.info("DistricService.readByUUID()");

        Distric model = searchEntityByUUID(uuid);

        return districtMapper.toDTO(model);
    }

    @Override
    public void remove(UUID uuid) {
        log.info("DistricService.remove()");

        Distric model_existente = searchEntityByUUID(uuid);
        // Cambiamos el estado del distrito a eliminado
        model_existente.setState_entity_id(StateEntity.builder().state_entity_id(3).build());

        districRepository.save(model_existente);
    }

    @Override
    public DistrictResponseDTO updateByUUID(UUID uuid, DistrictRequestDTO dto) {
        log.info("DistricService.updateByUUID()");
        // Buscamos el distrito por su UUID
        Distric model_existente = searchEntityByUUID(uuid);
        // Actualizamos el estado del tipo de persona si se requiere
        if (dto.getState_entity_uuid() != null) {
            StateEntity state_entity_exiting = stateEntityRepository.findByUuid(dto.getState_entity_uuid())
                    .orElseThrow(() -> new EServiceLayer("El estado de entidad no existe"));

            model_existente.setState_entity_id(state_entity_exiting);
        }
        // Actualizamos los datos
        districtMapper.updateFromDto(dto, model_existente);

        // Guardamos los cambios
        Distric model_guardado = districRepository.save(model_existente);
        // Retornamos el DTO con los datos actualizados
        return districtMapper.toDTO(model_guardado);
    }

    /**
     * Busca el distrito por su UUID
     * @param uuid
     * @return
     */
    private Distric searchEntityByUUID(UUID uuid) {
        return districRepository.findByUuid(uuid)
                .filter(entity -> entity.getState_entity_id().getState_entity_id()!= 3)
                .orElseThrow(
                        () -> {
                            if (districRepository.findByUuid(uuid).isPresent()) {
                                // El empleado existe, pero fue filtrado (estado == 3)
                                throw new EServiceLayer("El distrito está eliminado");
                            } else {
                                // El empleado nunca fue encontrado
                                return new EServiceLayer(
                                        String.format("No se encontró el distrito identificación con el id público: %s", uuid)
                                );
                            }
                        }
                );
    }
}
