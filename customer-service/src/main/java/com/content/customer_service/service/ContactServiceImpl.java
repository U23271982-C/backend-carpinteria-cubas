
package com.content.customer_service.service;

import com.content.customer_service.dto.request.ContactRequestDTO;
import com.content.customer_service.dto.response.ContactResponseDTO;
import com.content.customer_service.mapper.ContactMapper;
import com.content.customer_service.model.Client;
import com.content.customer_service.model.Contact;
import com.content.customer_service.model.StateEntity;
import com.content.customer_service.repository.ClientRepository;
import com.content.customer_service.repository.ContactRepository;
import com.content.customer_service.repository.StateEntityRepository;
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
 * Servicio de implementación para gestión de Contactos
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ServiceAbs<ContactRequestDTO, ContactResponseDTO> {

    private final ContactRepository contactRepository;
    private final ClientRepository clientRepository;
    private final StateEntityRepository stateEntityRepository;
    private final ContactMapper contactMapper;
    private final UtilityValidator utilityValidator;

    @Transactional
    @Override
    public ContactResponseDTO create(ContactRequestDTO dto) {
        log.info("Iniciando creación de contacto para cliente ID: {}", dto.getClient_id());

        // Validar datos del contacto
        utilityValidator.validate(dto);

        // Verificar que el cliente existe
        Client client = clientRepository.findById(dto.getClient_id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + dto.getClient_id()));

        // Verificar que el email no está registrado
        if (contactRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Ya existe un contacto con el email: " + dto.getEmail());
        }

        // Obtener estado activo (ID = 1)
        StateEntity stateActive = stateEntityRepository.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("Estado activo no encontrado"));

        // Crear el contacto
        Contact contact = contactMapper.toModel(dto);
        contact.setClient_id(client);
        contact.setState_entity_id(stateActive);

        Contact savedContact = contactRepository.save(contact);
        log.info("Contacto creado exitosamente con ID: {}", savedContact.getContact_id());

        return contactMapper.toDTO(savedContact);
    }

    @Transactional
    @Override
    public List<ContactResponseDTO> allList() {
        log.info("Obteniendo todos los contactos");
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream()
                .map(contactMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public ContactResponseDTO readById(Long id) {
        log.info("Buscando contacto con ID: {}", id);
        Optional<Contact> contactOpt = contactRepository.findById(id.intValue());

        if (contactOpt.isEmpty()) {
            log.error("Contacto no encontrado con ID: {}", id);
            throw new EntityNotFoundException("Contacto no encontrado con ID: " + id);
        }

        return contactMapper.toDTO(contactOpt.get());
    }

    @Transactional
    @Override
    public void remove(int id) {
        log.info("Eliminando (lógicamente) contacto con ID: {}", id);

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contacto no encontrado con ID: " + id));

        // Eliminación lógica: cambiar estado a eliminado (ID = 0)
        StateEntity stateDeleted = stateEntityRepository.findById(0)
                .orElseThrow(() -> new EntityNotFoundException("Estado eliminado no encontrado"));

        contact.setState_entity_id(stateDeleted);
        contactRepository.save(contact);
        log.info("Contacto eliminado (lógicamente) exitosamente con ID: {}", id);
    }

    @Transactional
    @Override
    public ContactResponseDTO update(int id, ContactRequestDTO dto) {
        log.info("Actualizando contacto con ID: {}", id);

        // Validar datos del contacto
        utilityValidator.validate(dto);

        // Verificar que el contacto existe
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contacto no encontrado con ID: " + id));
        // Verificar que el cliente existe
        Client client = clientRepository.findById(dto.getClient_id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + dto.getClient_id()));

        // Actualizar datos del contacto
        existingContact.setClient_id(client);
        existingContact.setPhone_number(dto.getPhone_number());
        existingContact.setEmail(dto.getEmail());

        Contact updatedContact = contactRepository.save(existingContact);
        log.info("Contacto actualizado exitosamente con ID: {}", updatedContact.getContact_id());

        return contactMapper.toDTO(updatedContact);
    }
}


