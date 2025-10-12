package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.UserClientRequestDTO;
import com.content.authentication_service.dto.response.UserClientResponseDTO;
import com.content.authentication_service.mapper.UserClientMapper;
import com.content.authentication_service.model.StateEntity;
import com.content.authentication_service.model.UserClient;
import com.content.authentication_service.repository.StateEntityRepository;
import com.content.authentication_service.repository.UserClientRepository;
import com.content.authentication_service.service.abstractservice.FireBaseService;
import com.content.authentication_service.service.abstractservice.ServiceAbs;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserClientServiceImpl implements ServiceAbs<UserClientRequestDTO, UserClientResponseDTO> {

    private final UserClientRepository userClientRepository;
    private final StateEntityRepository stateEntityRepository;
    private final UserClientMapper userClientMapper;
    private final FireBaseService firebaseService;


    // --- MÉTODOS REQUERIDOS POR LA INTERFAZ ServiceAbs (usando Integer id) ---

    @Override
    @Transactional
    public UserClientResponseDTO create(UserClientRequestDTO requestDTO) {
        UserRecord firebaseUser = firebaseService.createUserInFirebase(
                requestDTO.getEmail(),
                requestDTO.getPassword(),
                requestDTO.getFullName()
        );
        UserClient userClient = userClientMapper.toModel(requestDTO);
        userClient.setFireBaseUid(firebaseUser.getUid());
        StateEntity activeState = stateEntityRepository.findById(1) // Asumiendo que el ID 1 corresponde a 'ACTIVE'
                .orElseThrow(() -> new IllegalStateException("Default state 'ACTIVE' not found in database."));
        userClient.setUser_client_created_at(LocalDateTime.now());
        userClient.setState_entity_id(activeState);
        UserClient savedUser = userClientRepository.save(userClient);
        return userClientMapper.toDTO(savedUser);
    }

    /**
     * Implementación requerida por la interfaz Readable. Busca por la clave primaria de la DB.
     */
    @Override
    @Transactional(readOnly = true)
    public UserClientResponseDTO readById(Long id) {
        return userClientRepository.findById(id.intValue())
                .map(userClientMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User with local ID " + id + " not found."));
    }

    /**
     * Implementación requerida por la interfaz Updatable.
     * Primero busca al usuario por su ID local para obtener el UID de Firebase y luego actualiza ambos sistemas.
     */
    @Override
    @Transactional
    public UserClientResponseDTO update(int id, UserClientRequestDTO requestDTO) {
        // 1. Buscar el usuario en la DB local por su ID numérico
        UserClient userToUpdate = userClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with local ID " + id + " not found."));

        // 2. Obtener el UID de Firebase y actualizar allí
        String uid = userToUpdate.getFireBaseUid();
        firebaseService.updateUserInFirebase(uid, requestDTO.getEmail(), requestDTO.getPassword(), requestDTO.getFullName());

        // 3. Actualizar los campos del usuario local
        userToUpdate.setUser_client_email(requestDTO.getEmail());
        userToUpdate.setUser_client_full_name(requestDTO.getFullName());
        userToUpdate.setUser_client_phone(requestDTO.getPhone());
        userToUpdate.setUser_client_address(requestDTO.getAddress());

        UserClient updatedUser = userClientRepository.save(userToUpdate);
        return userClientMapper.toDTO(updatedUser);
    }

    /**
     * Implementación requerida por la interfaz Removable.
     * Realiza un soft-delete cambiando el estado y elimina el usuario de Firebase.
     */
    @Override
    @Transactional
    public void remove(int id) {
        // 1. Buscar el usuario en la DB local por su ID numérico
        UserClient userToDelete = userClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with local ID " + id + " not found."));

        // 2. Obtener el UID de Firebase y eliminar allí
        String uid = userToDelete.getFireBaseUid();
        firebaseService.deleteUserInFirebase(uid);

        // 3. Realizar el "soft delete" en la base de datos local
        StateEntity inactiveState = stateEntityRepository.findById(2) // Asumiendo que el ID 2 corresponde a 'INACTIVE'
                .orElseThrow(() -> new IllegalStateException("State 'INACTIVE' not found in database."));
        userToDelete.setState_entity_id(inactiveState);
        userClientRepository.save(userToDelete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserClientResponseDTO> allList() {
        return userClientRepository.findAll()
                .stream()
                .map(userClientMapper::toDTO)
                .collect(Collectors.toList());
    }


    // --- MÉTODOS ADICIONALES Y ESPECÍFICOS PARA ESTE SERVICIO (usando String uid) ---
    // Nota: Se elimina @Override porque no provienen de la interfaz ServiceAbs.

    /**
     * Método específico y más eficiente para buscar un usuario por su UID de Firebase.
     */
    public UserClientResponseDTO findByUid(String uid) {
        return userClientRepository.findByFireBaseUid(uid)
                .map(userClientMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User with UID " + uid + " not found."));
    }

    /**
     * Método específico y más eficiente para actualizar un usuario por su UID de Firebase.
     */
    public UserClientResponseDTO updateByUid(String uid, UserClientRequestDTO requestDTO) {
        firebaseService.updateUserInFirebase(uid, requestDTO.getEmail(), requestDTO.getPassword(), requestDTO.getFullName());
        UserClient userToUpdate = userClientRepository.findByFireBaseUid(uid)
                .orElseThrow(() -> new RuntimeException("User with UID " + uid + " not found locally."));
        userToUpdate.setUser_client_email(requestDTO.getEmail());
        userToUpdate.setUser_client_full_name(requestDTO.getFullName());
        userToUpdate.setUser_client_phone(requestDTO.getPhone());
        userToUpdate.setUser_client_address(requestDTO.getAddress());
        UserClient updatedUser = userClientRepository.save(userToUpdate);
        return userClientMapper.toDTO(updatedUser);
    }

    /**
     * Método específico y más eficiente para eliminar un usuario por su UID de Firebase.
     */
    public void removeByUid(String uid) {
        firebaseService.deleteUserInFirebase(uid);
        UserClient userToDelete = userClientRepository.findByFireBaseUid(uid)
                .orElseThrow(() -> new RuntimeException("User with UID " + uid + " not found locally."));
        StateEntity inactiveState = stateEntityRepository.findById(2) // Asumiendo que el ID 2 corresponde a 'INACTIVE'
                .orElseThrow(() -> new IllegalStateException("State 'INACTIVE' not found in database."));
        userToDelete.setState_entity_id(inactiveState);
        userClientRepository.save(userToDelete);
    }
}