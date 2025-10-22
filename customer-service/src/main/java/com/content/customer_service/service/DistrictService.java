package com.content.customer_service.service;

import com.content.customer_service.dto.request.DistrictRequestDTO;
import com.content.customer_service.dto.response.DistrictResponseDTO;
import com.content.customer_service.mapper.mapperImpl.DistrictMapper;
import com.content.customer_service.model.District;
import com.content.customer_service.model.Province;
import com.content.customer_service.model.StateEntity;
import com.content.customer_service.repository.DistrictRepository;
import com.content.customer_service.repository.ProvinceRepository;
import com.content.customer_service.service.abstractService.ServiceAbs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DistrictService implements ServiceAbs<DistrictRequestDTO, DistrictResponseDTO> {
    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;
    private final ProvinceRepository provinceRepository;

    @Transactional
    @Override
    public DistrictResponseDTO create(DistrictRequestDTO dto) {
        log.info("DistrictService.create()");
        Province province_reading =
                provinceRepository.findByUuid(dto.getProvince_uuid())
                        .orElseThrow(() -> new RuntimeException("La provincia no existe"));
        // Convertimos de DTO a modelo
        District model = districtMapper.toModel(dto);
        // Asignamos el UUID, el estado del distrito y la provincia
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build());
        model.setProvince_id(province_reading);
        model = districtRepository.save(model);
        return districtMapper.toDTO(model);
    }

    @Transactional
    @Override
    public List<DistrictResponseDTO> allList() {
        log.info("DistrictService.allList()");
        return districtRepository.findAll().stream()
                .filter(district -> district.getState_entity_id().getState_entity_id() != 3)
                .map(districtMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public DistrictResponseDTO readByUUID(UUID uuid) {
        log.info("DistrictService.readByUUID()");
        District model = searchEntityByUUID(uuid);
        return districtMapper.toDTO(model);
    }

    @Transactional
    @Override
    public void remove(UUID uuid) {
        log.info("DistrictService.remove()");
        District model = searchEntityByUUID(uuid);
        model.setState_entity_id(StateEntity.builder().state_entity_id(3).build());
        districtRepository.save(model);
    }

    @Transactional
    @Override
    public DistrictResponseDTO updateByUUID(UUID uuid, DistrictRequestDTO dto) {
        log.info("DistrictService.updateByUUID()");
         District model_existing = searchEntityByUUID(uuid);
         if (dto.getProvince_uuid() != null) {
                Province province_reading =
                        provinceRepository.findByUuid(dto.getProvince_uuid())
                                .orElseThrow(() -> new RuntimeException("La provincia no existe"));
                model_existing.setProvince_id(province_reading);
         }
         districtMapper.updateFromDto(dto, model_existing);
         District model_updated = districtRepository.save(model_existing);
         return districtMapper.toDTO(model_updated);
    }

    private District searchEntityByUUID(UUID uuid) {
        log.info("DistrictService.searchEntityByUUID()");
        return districtRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("El distrito no existe"));
    }
}
