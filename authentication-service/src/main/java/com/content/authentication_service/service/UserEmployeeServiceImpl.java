package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.UserEmployeeRequestDTO;
import com.content.authentication_service.dto.response.UserEmployeeResponseDTO;
import com.content.authentication_service.mapper.UserEmployeeMapper;
import com.content.authentication_service.model.*;
import com.content.authentication_service.model.Module;
import com.content.authentication_service.repository.UserEmployeeRepository;
import com.content.authentication_service.service.abstractservice.ServiceAbs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    private final UserEmployeeMapper userEmployeeMapper;
    private final UserEmployeeRepository userEmployeeRepository;
    private final StateEntityServiceImpl stateEntityService;
    private final UserEmployeePositionServiceImpl userEmployeePositionServiceImpl;
    private final UserModuleAccessServiceImpl userModuleAccessServiceImpl;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserEmployeeResponseDTO create(UserEmployeeRequestDTO dto) {
        // Convertir DTO a entidad
        UserEmployee userEmployee = userEmployeeMapper.toModel(dto);
        // Generar y asignar UUID
        UUID uuid = UUID.randomUUID();
        userEmployee.setUuid(uuid);

        if (userEmployee.getState_entity_id().getUuid() == null) {
            log.info("No se proporcionó state_entity_id, asignando estado predeterminado (activo)");
            userEmployee.setState_entity_id(stateEntityService.getStateActive());
        }

        if (userEmployee.getUser_employee_position_id().getUuid() != null) {
            log.info("No se proporcionó user_employee_position_id");
            userEmployee.setUser_employee_position_id(userEmployeePositionServiceImpl.getByUUIDActive(userEmployee.getUser_employee_position_id().getUuid()));
        } else {
            throw new RuntimeException("user_employee_position_id es obligatorio");
        }

        if (userEmployee.getFull_name() == null || userEmployee.getFull_name().isEmpty()) {
            throw new RuntimeException("El nombre completo es obligatorio");
        }

        if (userEmployee.getUser_employee_name() == null || userEmployee.getUser_employee_name().isEmpty()) {
            throw new RuntimeException("El nombre de usuario es obligatorio");
        }

        if (userEmployee.getPassword() == null || userEmployee.getPassword().isEmpty()) {
            throw new RuntimeException("La contraseña es obligatoria");
        }
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
                .filter(userEmployee -> userEmployee.getState_entity_id().getState_entity_id() != 3)
                .map(userEmployeeMapper::toDTO)
                .toList(); // Excluir eliminados;
    }

    @Override
    public UserEmployeeResponseDTO readById(UUID uuid) {
        return userEmployeeMapper.toDTO(findByUuid(uuid));
    }

    @Override
    public void remove(UUID uuid) {
        UserEmployee userEmployee = findByUuid(uuid);
        userEmployee.setState_entity_id(stateEntityService.deleteEntity());
        userModuleAccessServiceImpl.removeByUserEmployeeUuid(uuid);
        userEmployeeRepository.save(userEmployee);
    }

    @Override
    public UserEmployeeResponseDTO update(UUID uuid, UserEmployeeRequestDTO dto) {
        UserEmployee existingUserEmployee = findByUuid(uuid);

        if (dto.getFull_name() != null) {
            existingUserEmployee.setFull_name(dto.getFull_name());
        }

        if (dto.getUser_name() != null) {
            existingUserEmployee.setUser_employee_name(dto.getUser_name());
        }
        if (dto.getPhone() != null) {
            existingUserEmployee.setUser_employee_phone(dto.getPhone());
        }
        if (dto.getPositionUUID() != null) {
            existingUserEmployee.setUser_employee_position_id(userEmployeePositionServiceImpl.getByUUIDActive(dto.getPositionUUID()));
        }
        if (dto.getStateUUID() != null) {
            existingUserEmployee.setState_entity_id(stateEntityService.getByUUID(dto.getStateUUID()));
        }
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existingUserEmployee.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return userEmployeeMapper.toDTO(userEmployeeRepository.save(existingUserEmployee));
    }

    public UserEmployee findByUuid(UUID uuid){
        return userEmployeeRepository.findAll()
                .stream()
                .filter(user -> user.getUuid().equals(uuid) &&
                        user.getState_entity_id().getState_entity_id() != 3)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con UUID: " + uuid));
    }

    public UserEmployee getByUserAndPassword(String username, String password){
        UserEmployee userEmployee = userEmployeeRepository.findAll()
                .stream()
                .filter(user -> user.getUser_employee_name().equals(username) &&
                        user.getState_entity_id().getState_entity_id() == 1)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con nombre de usuario: " + username));

        if (passwordEncoder.matches(password, userEmployee.getPassword())) {
            return userEmployee;
        } else {
            throw new RuntimeException("Contraseña incorrecta para el usuario: " + username);
        }
    }

    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEmployee userEmployee = userEmployeeRepository.findAll()
                .stream()
                .filter(user -> user.getUser_employee_name().equals(username) &&
                        user.getState_entity_id().getState_entity_id() != 3)
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorities = getUserAuthorities(userEmployee);

        return new org.springframework.security.core.userdetails.User(
                userEmployee.getUser_employee_name(),
                userEmployee.getPassword(),
                authorities
        );
    }

    /**
     * Método para obtener las autoridades (permisos) de un usuario basado en sus accesos a módulos y acciones.
     *
     * @param user El usuario del cual se obtendrán las autoridades.
     * @return Una lista de GrantedAuthority que representa los permisos del usuario.
     */


    private List<GrantedAuthority> getUserAuthorities(UserEmployee user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Recorrer todos los accesos a módulos del usuario
        for (UserModuleAccess moduleAccess : user.getUser_module_accesses()) {
            Module module = moduleAccess.getModule_id();
            String moduleName = module.getModule_name().toUpperCase().replace(" ", "_");

            // Recorrer las acciones asignadas en este módulo
            for (UserAccessAction accessAction : moduleAccess.getUser_access_actions()) {
                Action action = accessAction.getAction_id();
                String actionName = action.getAction_name().toUpperCase().replace(" ", "_");

                // Crear authority en el formato: [MODULO]_[ACCION]
                String permission = moduleName + "_" + actionName;
                authorities.add(new SimpleGrantedAuthority(permission));
            }
        }

        return authorities;
    }

    /**
     * Verifica si un usuario con el nombre de usuario dado existe y no está eliminado.
     *
     * @param userEmployeeName El nombre de usuario a verificar.
     * @return true si el usuario existe y no está eliminado; false en caso contrario.
     */

    public boolean existsByUserEmployeeName(String userEmployeeName){
        return userEmployeeRepository.findAll()
                .stream()
                .anyMatch(userEmployee -> userEmployee.getUser_employee_name().equals(userEmployeeName) &&
                        userEmployee.getState_entity_id().getState_entity_id() != 3);
    }


    /**
     * Guarda un objeto UserEmployee en el repositorio.
     *
     * @param userEmployee El objeto UserEmployee a guardar.
     */

    public void save(UserEmployee userEmployee){
        userEmployeeRepository.save(userEmployee);
    }

}
