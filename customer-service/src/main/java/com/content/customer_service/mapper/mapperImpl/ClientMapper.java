package com.content.customer_service.mapper.mapperImpl;

import com.content.customer_service.dto.request.ClientRequestDTO;
import com.content.customer_service.dto.response.ClientResponseDTO;
import com.content.customer_service.mapper.convert.Convert;
import com.content.customer_service.mapper.convert.UpdatePatch;
import com.content.customer_service.model.Client;
import com.content.customer_service.model.Contact;
import com.content.customer_service.model.Direction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper para conversión entre Client, ClientRequestDTO y ClientResponseDTO
 * ACTUALIZADO PARA TRABAJAR CON UUIDs
 */
@Mapper(componentModel = "spring")
public interface ClientMapper
        extends Convert<Client, ClientRequestDTO, ClientResponseDTO>,
        UpdatePatch<ClientRequestDTO, Client> {

    /**
     * Convierte un modelo Client a ClientResponseDTO
     * SOLO EXPONE UUIDs, NUNCA IDs INTERNOS
     */
    @Mapping(target = "client_uuid", source = "uuid")
    @Mapping(target = "client_name", source = "client_name")
    @Mapping(target = "client_last_name", source = "client_last_name")
    @Mapping(target = "registration_date", source = "registration_date")
    @Mapping(target = "client_type_uuid", source = "client_type_id.uuid")
    @Mapping(target = "identification_uuid", source = "identification_id.uuid")
    @Mapping(target = "state_entity_name", source = "state_entity_id.state_entity_name")
    @Mapping(target = "contacts", source = "contacts")
    @Mapping(target = "directions", source = "directions")
    @Override
    ClientResponseDTO toDTO(Client modelo);

    /**
     * Convierte un ClientRequestDTO a modelo Client
     * IMPORTANTE: No mapea UUIDs ni IDs internos, solo datos de negocio
     */
    @Mapping(source = "client_name", target = "client_name")
    @Mapping(source = "client_last_name", target = "client_last_name")
    @Mapping(target = "client_type_id.uuid", source = "client_type_uuid")
    @Mapping(target = "identification_id.uuid", source = "identification_uuid")
    @Mapping(target = "state_entity_id.uuid", source = "state_entity_uuid")
    @Override
    Client toModel(ClientRequestDTO dto);

    /**
     * Mapea una lista de Contact a ContactSummaryDTO
     */
    default List<ClientResponseDTO.ContactSummaryDTO> mapContacts(List<Contact> contacts) {
        if (contacts == null) {
            return null;
        }
        return contacts.stream()
                .map(this::mapContactToSummaryDTO)
                .toList();
    }

    /**
     * Mapea un Contact individual a ContactSummaryDTO
     */
    default ClientResponseDTO.ContactSummaryDTO mapContactToSummaryDTO(Contact contact) {
        if (contact == null) {
            return null;
        }

        ClientResponseDTO.ContactSummaryDTO dto = new ClientResponseDTO.ContactSummaryDTO();
        dto.setPhoneNumber(contact.getPhone_number());
        dto.setEmail(contact.getEmail());

        if (contact.getState_entity_id() != null) {
            dto.setState_entity_name(contact.getState_entity_id().getState_entity_name());
        }

        return dto;
    }

    /**
     * Mapea una lista de Direction a DirectionSummaryDTO
     */
    default List<ClientResponseDTO.DirectionSummaryDTO> mapDirections(List<Direction> directions) {
        if (directions == null) {
            return null;
        }
        return directions.stream()
                .map(this::mapDirectionToSummaryDTO)
                .toList();
    }

    /**
     * Mapea un Direction individual a DirectionSummaryDTO
     */
    default ClientResponseDTO.DirectionSummaryDTO mapDirectionToSummaryDTO(Direction direction) {
        if (direction == null) {
            return null;
        }

        ClientResponseDTO.DirectionSummaryDTO dto = new ClientResponseDTO.DirectionSummaryDTO();
        dto.setDirection_type_name(direction.getDirection_type_id() != null ?
                direction.getDirection_type_id().getDirection_type_name() : null);
        dto.setDirection_name(direction.getDirection_name());
        dto.setDirection_number(direction.getDirection_number());
        dto.setReference(direction.getReference());
        dto.setDistrict_name(direction.getDistrict_id() != null ?
                direction.getDistrict_id().getDistrict_name() : null);
        dto.setState_entity_name(direction.getState_entity_id() != null ?
                direction.getState_entity_id().getState_entity_name() : null);

        return dto;
    }

}
