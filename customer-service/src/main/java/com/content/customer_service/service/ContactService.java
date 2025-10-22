package com.content.customer_service.service;

import com.content.customer_service.dto.request.ContactRequestDTO;
import com.content.customer_service.dto.response.ContactResponseDTO;
import com.content.customer_service.exception.EServiceLayer;
import com.content.customer_service.exception.EValidation;
import com.content.customer_service.exception.ObjectErrorValidation;
import com.content.customer_service.mapper.mapperImpl.ContactMapper;
import com.content.customer_service.model.Client;
import com.content.customer_service.model.Contact;
import com.content.customer_service.model.StateEntity;
import com.content.customer_service.repository.ClientRepository;
import com.content.customer_service.repository.ContactRepository;
import com.content.customer_service.service.abstractService.ServiceAbs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
 * Servicio de implementación para gestión de Contactos
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService implements ServiceAbs<ContactRequestDTO, ContactResponseDTO> {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public ContactResponseDTO create(ContactRequestDTO dto) {
        log.info("ContactService.create()");
        Client client_reading =
                clientRepository.findByUuid(dto.getClient_uuid())
                        .orElseThrow(() -> new EServiceLayer("El cliente no existe"));
        Contact model = contactMapper.toModel(dto);
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build());
        model.setClient_id(client_reading);
        model = contactRepository.save(model);
        return contactMapper.toDTO(model);
    }

    @Transactional
    @Override
    public List<ContactResponseDTO> allList() {
        log.info("ContactService.allList()");
        return contactRepository.findAll().stream()
                .filter(contact -> contact.getState_entity_id().getState_entity_id() != 3)
                .map(contactMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public ContactResponseDTO readByUUID(UUID uuid) {
        log.info("ContactService.readByUUID()");
        Contact model = searchEntityByUUID(uuid);
        return contactMapper.toDTO(model);
    }

    @Transactional
    @Override
    public void remove(UUID uuid) {
        log.info("ContactService.remove()");
        Contact model = searchEntityByUUID(uuid);
        model.setState_entity_id(StateEntity.builder().state_entity_id(3).build());
        contactRepository.save(model);
    }

    @Transactional
    @Override
    public ContactResponseDTO updateByUUID(UUID uuid, ContactRequestDTO dto) {
        log.info("ContactService.updateByUUID()");
        Contact model_exiting = searchEntityByUUID(uuid);
        if (dto.getClient_uuid() != null) {
            Client client_reading =
                    clientRepository.findByUuid(dto.getClient_uuid())
                            .orElseThrow(() -> new EValidation(List.of(new ObjectErrorValidation("client_uuid", "El cliente no existe"))));
            model_exiting.setClient_id(client_reading);
        }

        Contact model_updated = contactRepository.save(model_exiting);
        return contactMapper.toDTO(model_updated);
    }

    private Contact searchEntityByUUID(UUID uuid) {
        return contactRepository.findByUuid(uuid)
                .orElseThrow(() -> new EServiceLayer("El contacto no existe"));
    }
}
