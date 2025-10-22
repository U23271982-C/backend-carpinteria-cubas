package com.content.customer_service.service;

import com.content.customer_service.dto.request.ProvinceRequestDTO;
import com.content.customer_service.dto.response.ProvinceResponseDTO;
import com.content.customer_service.exception.EServiceLayer;
import com.content.customer_service.mapper.mapperImpl.ProvinceMapper;
import com.content.customer_service.model.Department;
import com.content.customer_service.model.Province;
import com.content.customer_service.model.StateEntity;
import com.content.customer_service.repository.DepartmentRepository;
import com.content.customer_service.repository.ProvinceRepository;
import com.content.customer_service.service.abstractService.ServiceAbs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProvinceService implements ServiceAbs<ProvinceRequestDTO, ProvinceResponseDTO> {

    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;
    private final DepartmentRepository departmentRepository;

    @Override
    public ProvinceResponseDTO create(ProvinceRequestDTO dto) {
        log.info("ProvinceService.create()");
        Department department_reading =
                departmentRepository.findByUuid(dto.getDepartment_UUID())
                        .orElseThrow(() -> new EServiceLayer("El departamento no existe"));
        Province model = provinceMapper.toModel(dto);
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build());
        model.setDepartment_id(department_reading);
        model = provinceRepository.save(model);
        return provinceMapper.toDTO(model);
    }

    @Override
    public List<ProvinceResponseDTO> allList() {
        log.info("ProvinceService.allList()");
        return provinceRepository.findAll().stream()
                .filter(province -> province.getState_entity_id().getState_entity_id() != 3)
                .map(provinceMapper::toDTO)
                .toList();
    }

    @Override
    public ProvinceResponseDTO readByUUID(UUID uuid) {
        log.info("ProvinceService.readByUUID()");
        Province model = searchEntityByUUID(uuid);
        return provinceMapper.toDTO(model);
    }

    @Override
    public void remove(UUID uuid) {
        log.info("ProvinceService.remove()");
        Province model_exiting = searchEntityByUUID(uuid);
        model_exiting.setState_entity_id(StateEntity.builder().state_entity_id(3).build());
        provinceRepository.save(model_exiting);
    }

    @Override
    public ProvinceResponseDTO updateByUUID(UUID uuid, ProvinceRequestDTO dto) {
        log.info("ProvinceService.updateByUUID()");
        Province model_exiting = searchEntityByUUID(uuid);
        if (dto.getDepartment_UUID() != null) {
            Department department_reading =
                    departmentRepository.findByUuid(dto.getDepartment_UUID())
                            .orElseThrow(() -> new EServiceLayer("El departamento no existe"));
            model_exiting.setDepartment_id(department_reading);
        }
        provinceMapper.updateFromDto(dto, model_exiting);
        Province model_updated = provinceRepository.save(model_exiting);
        return provinceMapper.toDTO(model_updated);
    }

    private Province searchEntityByUUID(UUID uuid) {
        log.info("ProvinceService.searchEntityByUUID()");
        return provinceRepository.findByUuid(uuid)
                .orElseThrow(() -> new EServiceLayer(String.format("La provincia con UUID %s no existe", uuid)));
    }
}
