package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.UserEmployeeRequestDTO;
import com.content.authentication_service.dto.response.UserEmployeeResponseDTO;
import com.content.authentication_service.mapper.UserEmployeeMapper;
import com.content.authentication_service.model.*;
import com.content.authentication_service.model.Module;
import com.content.authentication_service.repository.StateEntityRepository;
import com.content.authentication_service.repository.UserEmployeePositionRepository;
import com.content.authentication_service.repository.UserEmployeeRepository;
import com.content.authentication_service.repository.UserModuleAccessRepository;
import com.content.authentication_service.service.abstractservice.ServiceAbs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserEmployeeServiceImpl implements ServiceAbs<UserEmployeeRequestDTO, UserEmployeeResponseDTO>, UserDetailsService {

    private final UserEmployeeRepository userEmployeeRepository;
    private final StateEntityRepository stateEntityRepository;
    private final UserModuleAccessRepository userModuleAccessRepository;
    private final UserEmployeePositionRepository userEmployeePositionRepository;
    private final UserEmployeeMapper userEmployeeMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserEmployeeResponseDTO create(UserEmployeeRequestDTO dto) {
        // Convertir DTO a entidad
        if (userEmployeeRepository.findByUsername(dto.getUser_name()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        UserEmployeePosition userEmployeePosition = userEmployeePositionRepository.findByUuid(dto.getPositionUUID()).orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));
        if (dto.getStateUUID() != null) {
            throw  new RuntimeException("El State Entity no debe ser proporcionado al crear un usuario empleado");
        }
        if (userEmployeePosition.getState_entity_id().getStateId() == 1) {
            throw new RuntimeException("La posición del empleado no es válida o está inactiva");
        }
        if (dto.getFull_name()== null || dto.getFull_name().isEmpty()) {
            throw new RuntimeException("El nombre completo es obligatorio");
        }

        if (dto.getUser_name() == null || dto.getUser_name().isEmpty()) {
            throw new RuntimeException("El nombre de usuario es obligatorio");
        }

        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            throw new RuntimeException("La contraseña es obligatoria");
        }
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new RuntimeException("Las contraseñas no coinciden");
        }
        UserEmployee userEmployee = userEmployeeMapper.toModel(dto);
        UUID uuid = UUID.randomUUID();
        userEmployee.setUuid(uuid);
        userEmployee.setState_entity_id(stateEntityRepository.findByStateId(1).orElse(null)); // Asignar estado activo por defecto
        userEmployee.setUser_employee_position_id(userEmployeePosition);
        String encryptedPassword = passwordEncoder.encode(userEmployee.getPassword());
        userEmployee.setPassword(encryptedPassword);

        // Guardar entidad en la base de datos
        UserEmployee savedUserEmployee = userEmployeeRepository.save(userEmployee);
        // Convertir entidad guardada a DTO y retornarlo
        return userEmployeeMapper.toDTO(savedUserEmployee);
    }

    @Override
    public List<UserEmployeeResponseDTO> allList() {
        return userEmployeeRepository.findAll()
                .stream()
                .filter(userEmployee -> userEmployee.getState_entity_id().getStateId() != 3)
                .map(userEmployeeMapper::toDTO)
                .toList(); // Excluir eliminados;
    }

    @Override
    public UserEmployeeResponseDTO readById(UUID uuid) {
        return userEmployeeMapper.toDTO(userEmployeeRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("Usuario empleado no encontrado")));
    }

    @Override
    public void remove(UUID uuid) {
        UserEmployee userEmployee = userEmployeeRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("Usuario empleado no encontrado"));
        userEmployee.setState_entity_id(stateEntityRepository.findByStateId(3).orElseThrow());
        List<UserModuleAccess> userModuleAccessList = userModuleAccessRepository.findAll()
                .stream()
                .filter(uma -> uma.getUser_employee_id().equals(userEmployee))
                .toList();
        userModuleAccessRepository.deleteAll(userModuleAccessList);
        userEmployeeRepository.save(userEmployee);
    }

    @Override
    public UserEmployeeResponseDTO update(UUID uuid, UserEmployeeRequestDTO dto) {
        UserEmployee existingUserEmployee = userEmployeeRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("Usuario empleado no encontrado"));
        UserEmployeePosition userEmployeePosition = userEmployeePositionRepository.findByUuid(dto.getPositionUUID()).orElseThrow(()-> new RuntimeException("El position no existe"));

        if (existingUserEmployee == null) {
            throw new RuntimeException("Usuario empleado no encontrado");
        }
        if (dto.getFull_name() != null) {
            existingUserEmployee.setFull_name(dto.getFull_name());
        }
        if (dto.getUser_name() != null) {
            existingUserEmployee.setUsername(dto.getUser_name());
        }
        if (dto.getPhone() != null) {
            existingUserEmployee.setUser_employee_phone(dto.getPhone());
        }
        if (userEmployeePosition.getState_entity_id().getStateId() == 1) {
            existingUserEmployee.setUser_employee_position_id(userEmployeePosition);
        }
        if (dto.getStateUUID() != null) {
            existingUserEmployee.setState_entity_id(stateEntityRepository.findByUuid(dto.getStateUUID()).orElseThrow(()-> new RuntimeException("Estado entidad no encontrado")));
        }
        if (dto.getPassword() != null && !dto.getPassword().isEmpty() && dto.getPassword().equals(dto.getConfirmPassword())) {
            existingUserEmployee.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return userEmployeeMapper.toDTO(userEmployeeRepository.save(existingUserEmployee));
    }



    /**
     *
     * @param uuid
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        UserEmployee userEmployee = userEmployeeRepository.findByUuid(UUID.fromString(uuid)).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado" + uuid));
        if (userEmployee.getState_entity_id().getStateId() == 2) {
            throw new UsernameNotFoundException("El usuario no está activo: " + userEmployee.getUsername());
        }
        if (userEmployee.getState_entity_id().getStateId() == 3) {
            throw new UsernameNotFoundException("El usuario ha sido bloqueado: " + userEmployee.getUsername());
        }
        /**
         * Construir la lista de autoridades (permisos) del usuario en base a sus accesos a módulos y acciones
         */

        List<GrantedAuthority> authorities = new ArrayList<>();
        // Recorrer todos los accesos a módulos del usuario
        for (UserModuleAccess moduleAccess : userEmployee.getUser_module_accesses()) {
            Module module = moduleAccess.getModule_id();
            String moduleName = module.getName().toUpperCase().replace(" ", "_");

            // Recorrer las acciones asignadas en este módulo
            for (UserAccessAction accessAction : moduleAccess.getUser_access_actions()) {
                Action action = accessAction.getActionId();
                String actionName = action.getAction_name().toUpperCase().replace(" ", "_");

                // Crear authority en el formato: [MODULO]_[ACCION]
                String permission = moduleName + "_" + actionName;
                authorities.add(new SimpleGrantedAuthority(permission));
            }
        }
        return new User(userEmployee.getUuid().toString(), userEmployee.getPassword(), authorities);
    }
}
