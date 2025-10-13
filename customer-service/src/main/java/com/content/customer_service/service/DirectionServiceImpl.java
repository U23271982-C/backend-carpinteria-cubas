package com.content.customer_service.service;

import com.content.customer_service.dto.request.DirectionRequestDTO;
import com.content.customer_service.dto.response.DirectionResponseDTO;
import com.content.customer_service.mapper.DirectionMapper;
import com.content.customer_service.model.*;
import com.content.customer_service.repository.*;
import com.content.customer_service.service.abstractService.ServiceAbs;
import com.content.customer_service.util.UtilityValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de implementación para gestión de Direcciones
 * Incluye lógica automática para crear Departamento, Provincia y Distrito si no existen
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DirectionServiceImpl implements ServiceAbs<DirectionRequestDTO, DirectionResponseDTO> {

    private final DirectionRepository directionRepository;
    private final ClientRepository clientRepository;
    private final DirectionTypeRepository directionTypeRepository;
    private final DepartmentRepository departmentRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final StateEntityRepository stateEntityRepository;
    private final DirectionMapper directionMapper;
    private final UtilityValidator utilityValidator;

    @Transactional
    @Override
    public DirectionResponseDTO create(DirectionRequestDTO dto) {
        log.info("Iniciando creación de dirección para cliente ID: {}", dto.getClient_id());

        // Validar datos de la dirección
        utilityValidator.validate(dto);

        // Verificar que el cliente existe
        Client client = clientRepository.findById(dto.getClient_id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + dto.getClient_id()));

        // Verificar que el tipo de dirección existe
        DirectionType directionType = directionTypeRepository.findById(dto.getDirection_type_id())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de dirección no encontrado con ID: " + dto.getDirection_type_id()));

        // Obtener estado activo (ID = 1)
        StateEntity stateActive = stateEntityRepository.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("Estado activo no encontrado"));

        // LÓGICA DE NEGOCIO: Buscar o crear Departamento, Provincia y Distrito
        Department department = findOrCreateDepartment(dto.getDepartment_name(), stateActive);
        Province province = findOrCreateProvince(dto.getProvince_name(), department, stateActive);
        District district = findOrCreateDistrict(dto.getDistrict_name(), province, stateActive);

        // Crear la dirección
        Direction direction = directionMapper.toModel(dto);
        direction.setClient_id(client);
        direction.setDirection_type_id(directionType);
        direction.setDistrict_id(district);
        direction.setState_entity_id(stateActive);

        Direction savedDirection = directionRepository.save(direction);
        log.info("Dirección creada exitosamente con ID: {}", savedDirection.getDirection_id());

        return directionMapper.toDTO(savedDirection);
    }

    /**
     * Busca un departamento por nombre, si no existe lo crea
     */
    private Department findOrCreateDepartment(String departmentName, StateEntity stateActive) {
        log.info("Buscando o creando departamento: {}", departmentName);

        Optional<Department> departmentOpt = departmentRepository.findByDepartment_nameIgnoreCase(departmentName);

        if (departmentOpt.isPresent()) {
            log.info("Departamento encontrado: {}", departmentName);
            return departmentOpt.get();
        }

        // Crear nuevo departamento
        Department newDepartment = Department.builder()
                .department_name(departmentName)
                .state_entity_id(stateActive)
                .build();

        Department savedDepartment = departmentRepository.save(newDepartment);
        log.info("Departamento creado con ID: {}", savedDepartment.getDepartment_id());
        return savedDepartment;
    }

    /**
     * Busca una provincia por nombre y departamento, si no existe la crea
     */
    private Province findOrCreateProvince(String provinceName, Department department, StateEntity stateActive) {
        log.info("Buscando o creando provincia: {} en departamento: {}", provinceName, department.getDepartment_name());

        Optional<Province> provinceOpt = provinceRepository
                .findByProvince_nameIgnoreCaseAndDepartment_id_Department_id(provinceName, department.getDepartment_id());

        if (provinceOpt.isPresent()) {
            log.info("Provincia encontrada: {}", provinceName);
            return provinceOpt.get();
        }

        // Crear nueva provincia
        Province newProvince = Province.builder()
                .province_name(provinceName)
                .department_id(department)
                .state_entity_id(stateActive)
                .build();

        Province savedProvince = provinceRepository.save(newProvince);
        log.info("Provincia creada con ID: {}", savedProvince.getProvince_id());
        return savedProvince;
    }

    /**
     * Busca un distrito por nombre y provincia, si no existe lo crea
     */
    private District findOrCreateDistrict(String districtName, Province province, StateEntity stateActive) {
        log.info("Buscando o creando distrito: {} en provincia: {}", districtName, province.getProvince_name());

        Optional<District> districtOpt = districtRepository
                .findByDistrict_nameIgnoreCaseAndProvince_id_Province_id(districtName, province.getProvince_id());

        if (districtOpt.isPresent()) {
            log.info("Distrito encontrado: {}", districtName);
            return districtOpt.get();
        }

        // Crear nuevo distrito
        District newDistrict = District.builder()
                .district_name(districtName)
                .province_id(province)
                .state_entity_id(stateActive)
                .build();

        District savedDistrict = districtRepository.save(newDistrict);
        log.info("Distrito creado con ID: {}", savedDistrict.getDistrict_id());
        return savedDistrict;
    }

    @Transactional
    @Override
    public List<DirectionResponseDTO> allList() {
        log.info("Obteniendo todas las direcciones");
        List<Direction> directions = directionRepository.findAll();
        return directions.stream()
                .map(directionMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public DirectionResponseDTO readById(Long id) {
        log.info("Buscando dirección con ID: {}", id);
        Optional<Direction> directionOpt = directionRepository.findById(id.intValue());

        if (directionOpt.isEmpty()) {
            log.error("Dirección no encontrada con ID: {}", id);
            throw new EntityNotFoundException("Dirección no encontrada con ID: " + id);
        }

        return directionMapper.toDTO(directionOpt.get());
    }

    @Transactional
    @Override
    public void remove(int id) {
        log.info("Eliminando (lógicamente) dirección con ID: {}", id);

        Direction direction = directionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada con ID: " + id));

        // Eliminación lógica: cambiar estado a eliminado (ID = 0)
        StateEntity stateDeleted = stateEntityRepository.findById(0)
                .orElseThrow(() -> new EntityNotFoundException("Estado eliminado no encontrado"));

        direction.setState_entity_id(stateDeleted);
        directionRepository.save(direction);
        log.info("Dirección eliminada (lógicamente) exitosamente con ID: {}", id);
    }

    @Transactional
    @Override
    public DirectionResponseDTO update(int id, DirectionRequestDTO dto) {
        log.info("Actualizando dirección con ID: {}", id);

        // Validar datos de la dirección
        utilityValidator.validate(dto);

        // Verificar que la dirección existe
        Direction existingDirection = directionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada con ID: " + id));

        // Verificar que el cliente existe
        Client client = clientRepository.findById(dto.getClient_id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + dto.getClient_id()));

        // Verificar que el tipo de dirección existe
        DirectionType directionType = directionTypeRepository.findById(dto.getDirection_type_id())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de dirección no encontrado con ID: " + dto.getDirection_type_id()));

        // Obtener estado activo
        StateEntity stateActive = stateEntityRepository.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("Estado activo no encontrado"));

        // Buscar o crear ubicación geográfica
        Department department = findOrCreateDepartment(dto.getDepartment_name(), stateActive);
        Province province = findOrCreateProvince(dto.getProvince_name(), department, stateActive);
        District district = findOrCreateDistrict(dto.getDistrict_name(), province, stateActive);

        // Actualizar datos de la dirección
        existingDirection.setClient_id(client);
        existingDirection.setDirection_type_id(directionType);
        existingDirection.setDirection_name(dto.getDirection_name());
        existingDirection.setDirection_number(dto.getDirection_number());
        existingDirection.setReference(dto.getReference());
        existingDirection.setDistrict_id(district);

        Direction updatedDirection = directionRepository.save(existingDirection);
        log.info("Dirección actualizada exitosamente con ID: {}", updatedDirection.getDirection_id());

        return directionMapper.toDTO(updatedDirection);
    }
}

