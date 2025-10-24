package com.content.employee_service.service;

import com.content.employee_service.dto.request.PostRequestDTO;
import com.content.employee_service.dto.response.PostResponseDTO;
import com.content.employee_service.exception.EServiceLayer;
import com.content.employee_service.mapper.mapperImpl.PostMapper;
import com.content.employee_service.model.Post;
import com.content.employee_service.model.StateEntity;
import com.content.employee_service.repository.PostRepository;
import com.content.employee_service.repository.StateEntityRepository;
import com.content.employee_service.service.abstractService.ServiceAbs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@Slf4j
@RequiredArgsConstructor
public class PostService implements ServiceAbs<PostRequestDTO, PostResponseDTO> {
    private final PostRepository repository;
    private final PostMapper mapper;

    private final StateEntityRepository stateEntityRepository;
    @Override
    public PostResponseDTO create(PostRequestDTO dto) {
        log.info("PostService.create()");
        // Convertimos de DTO a modelo
        Post model = mapper.toModel(dto);
        // Asignamos un UUID y un estado
        model.setUuid(UUID.randomUUID());
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build()); // ACTIVO
        // Guardamos el modelo en la BD
        model = repository.save(model);

        return mapper.toDTO(model);
    }

    @Override
    public List<PostResponseDTO> allList() {
        log.info("PostService.allList()");
        return repository.findAll().stream()
                .filter(post -> post.getState_entity_id().getState_entity_id() != 3)
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public PostResponseDTO readByUUID(UUID uuid) {
        log.info("PostService.readByUUID()");

        Post model = searchEntityByUUID(uuid);

        return mapper.toDTO(model);
    }

    @Override
    public void remove(UUID uuid) {
        log.info("PostService.remove()");
        // Buscamos el tipo de contrato por su UUID
        Post model_existente = searchEntityByUUID(uuid);
        model_existente.setState_entity_id(StateEntity.builder().state_entity_id(3).build()); // Cambiamos a estado ELIMINADO
        repository.save(model_existente);
    }

    @Override
    public PostResponseDTO updateByUUID(UUID uuid, PostRequestDTO dto) {
        log.info("PostService.updateByUUID()");
        // Buscamos el tipo de contrato por su UUID
        Post model_existente = searchEntityByUUID(uuid);
        // Actualizamos el estado del tipo de persona si se requiere
        if (dto.getState_entity_uuid() != null) {
            StateEntity state_entity_exiting = stateEntityRepository.findByUuid(dto.getState_entity_uuid())
                    .orElseThrow(() -> new EServiceLayer("El estado de entidad no existe"));

            model_existente.setState_entity_id(StateEntity.builder()
                    // Agregamos el nuevo id del estado, para que se pueda asociar con FK
                    .state_entity_id(state_entity_exiting.getState_entity_id()).build());
        }
        // Actualizamos los datos
        mapper.updateFromDto(dto, model_existente);
        // Guardamos los cambios
        model_existente = repository.save(model_existente);
        // Retornamos el DTO con los datos actualizados
        return mapper.toDTO(model_existente);
    }

    private Post searchEntityByUUID(UUID uuid) {
        return repository.findByUuid(uuid)
                .filter(entity -> entity.getState_entity_id().getState_entity_id()!= 3)
                .orElseThrow(
                        () -> {
                            if (repository.findByUuid(uuid).isPresent()) {
                                // El empleado existe, pero fue filtrado (estado == 3)
                                throw new EServiceLayer("El cargo está eliminado");
                            } else {
                                // El empleado nunca fue encontrado
                                return new EServiceLayer(
                                        String.format("No se encontró el cargo con el id público: %s", uuid)
                                );
                            }
                        }
                );
    }
}
