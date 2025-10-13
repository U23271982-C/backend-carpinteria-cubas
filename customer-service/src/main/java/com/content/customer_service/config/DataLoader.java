package com.content.customer_service.config;

import com.content.customer_service.model.*;
import com.content.customer_service.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuración para cargar datos de prueba en la base de datos
 * Solo se ejecuta con el perfil "test"
 *
 * Para usar: agregar --spring.profiles.active=test al ejecutar la aplicación
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
@Profile("test") // Solo se activa con el perfil "test"
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(
            StateEntityRepository stateEntityRepository,
            PersonTypeRepository personTypeRepository,
            IdentificationTypeRepository identificationTypeRepository,
            IdentificationRepository identificationRepository,
            ClientTypeRepository clientTypeRepository,
            DirectionTypeRepository directionTypeRepository,
            DepartmentRepository departmentRepository,
            ProvinceRepository provinceRepository,
            DistrictRepository districtRepository,
            ClientRepository clientRepository,
            ContactRepository contactRepository,
            DirectionRepository directionRepository
    ) {
        return args -> {
            log.info("🚀 Iniciando carga de datos de prueba...");

            // ========== 1. ESTADOS ==========
            log.info("📊 Creando estados de entidades...");
            StateEntity stateDeleted = StateEntity.builder()
                    .state_entity_name("Eliminado")
                    .build();
            stateDeleted = stateEntityRepository.save(stateDeleted);

            StateEntity stateActive = StateEntity.builder()
                    .state_entity_name("Activo")
                    .build();
            stateActive = stateEntityRepository.save(stateActive);

            StateEntity stateInactive = StateEntity.builder()
                    .state_entity_name("Inactivo")
                    .build();
            stateInactive = stateEntityRepository.save(stateInactive);

            log.info("✅ Estados creados: Eliminado ({}), Activo ({}), Inactivo ({})",
                    stateDeleted.getState_entity_id(),
                    stateActive.getState_entity_id(),
                    stateInactive.getState_entity_id());

            // ========== 2. TIPOS DE PERSONA ==========
            log.info("👥 Creando tipos de persona...");
            PersonType personNatural = PersonType.builder()
                    .persona_type_name("Natural")
                    .state_entity_id(stateActive)
                    .build();
            personNatural = personTypeRepository.save(personNatural);

            PersonType personJuridica = PersonType.builder()
                    .persona_type_name("Jurídica")
                    .state_entity_id(stateActive)
                    .build();
            personJuridica = personTypeRepository.save(personJuridica);

            log.info("✅ Tipos de persona creados: Natural ({}), Jurídica ({})",
                    personNatural.getPerson_type_id(),
                    personJuridica.getPerson_type_id());

            // ========== 3. TIPOS DE IDENTIFICACIÓN ==========
            log.info("🆔 Creando tipos de identificación...");
            IdentificationType typeDNI = IdentificationType.builder()
                    .identification_type_name("DNI")
                    .person_type_id(personNatural)
                    .state_entity_id(stateActive)
                    .build();
            typeDNI = identificationTypeRepository.save(typeDNI);

            IdentificationType typeRUC = IdentificationType.builder()
                    .identification_type_name("RUC")
                    .person_type_id(personJuridica)
                    .state_entity_id(stateActive)
                    .build();
            typeRUC = identificationTypeRepository.save(typeRUC);

            IdentificationType typePasaporte = IdentificationType.builder()
                    .identification_type_name("Pasaporte")
                    .person_type_id(personNatural)
                    .state_entity_id(stateActive)
                    .build();
            typePasaporte = identificationTypeRepository.save(typePasaporte);

            log.info("✅ Tipos de identificación creados: DNI ({}), RUC ({}), Pasaporte ({})",
                    typeDNI.getIdentification_type_id(),
                    typeRUC.getIdentification_type_id(),
                    typePasaporte.getIdentification_type_id());

            // ========== 4. IDENTIFICACIONES ==========
            log.info("📄 Creando identificaciones de prueba...");
            Identification id1 = Identification.builder()
                    .identification_doc("72345678")
                    .identification_type_id(typeDNI)
                    .state_entity_id(stateActive)
                    .build();
            id1 = identificationRepository.save(id1);

            Identification id2 = Identification.builder()
                    .identification_doc("87654321")
                    .identification_type_id(typeDNI)
                    .state_entity_id(stateActive)
                    .build();
            id2 = identificationRepository.save(id2);

            Identification id3 = Identification.builder()
                    .identification_doc("20123456789")
                    .identification_type_id(typeRUC)
                    .state_entity_id(stateActive)
                    .build();
            id3 = identificationRepository.save(id3);

            log.info("✅ Identificaciones creadas: {} ({}), {} ({}), {} ({})",
                    id1.getIdentification_doc(), id1.getIdentification_id(),
                    id2.getIdentification_doc(), id2.getIdentification_id(),
                    id3.getIdentification_doc(), id3.getIdentification_id());

            // ========== 5. TIPOS DE CLIENTE ==========
            log.info("🏷️ Creando tipos de cliente...");
            ClientType typeRegular = ClientType.builder()
                    .client_type_name("Regular")
                    .description("Cliente regular sin beneficios especiales")
                    .state_entity_id(stateActive)
                    .build();
            typeRegular = clientTypeRepository.save(typeRegular);

            ClientType typePremium = ClientType.builder()
                    .client_type_name("Premium")
                    .description("Cliente con beneficios premium")
                    .state_entity_id(stateActive)
                    .build();
            typePremium = clientTypeRepository.save(typePremium);

            ClientType typeVIP = ClientType.builder()
                    .client_type_name("VIP")
                    .description("Cliente VIP con máximos beneficios")
                    .state_entity_id(stateActive)
                    .build();
            typeVIP = clientTypeRepository.save(typeVIP);

            log.info("✅ Tipos de cliente creados: Regular ({}), Premium ({}), VIP ({})",
                    typeRegular.getClient_type_id(),
                    typePremium.getClient_type_id(),
                    typeVIP.getClient_type_id());

            // ========== 6. TIPOS DE DIRECCIÓN ==========
            log.info("🏠 Creando tipos de dirección...");
            DirectionType typeCasa = DirectionType.builder()
                    .description("Casa")
                    .state_entity_id(stateActive)
                    .build();
            typeCasa = directionTypeRepository.save(typeCasa);

            DirectionType typeOficina = DirectionType.builder()
                    .description("Oficina")
                    .state_entity_id(stateActive)
                    .build();
            typeOficina = directionTypeRepository.save(typeOficina);

            DirectionType typeTrabajo = DirectionType.builder()
                    .description("Trabajo")
                    .state_entity_id(stateActive)
                    .build();
            typeTrabajo = directionTypeRepository.save(typeTrabajo);

            log.info("✅ Tipos de dirección creados: Casa ({}), Oficina ({}), Trabajo ({})",
                    typeCasa.getDirection_type_id(),
                    typeOficina.getDirection_type_id(),
                    typeTrabajo.getDirection_type_id());

            // ========== 7. UBICACIONES GEOGRÁFICAS ==========
            log.info("🗺️ Creando ubicaciones geográficas de prueba...");

            // Departamento Lima
            Department deptLima = Department.builder()
                    .department_name("Lima")
                    .state_entity_id(stateActive)
                    .build();
            deptLima = departmentRepository.save(deptLima);

            // Provincia Lima
            Province provLima = Province.builder()
                    .province_name("Lima")
                    .department_id(deptLima)
                    .state_entity_id(stateActive)
                    .build();
            provLima = provinceRepository.save(provLima);

            // Distritos de Lima
            District distMiraflores = District.builder()
                    .district_name("Miraflores")
                    .province_id(provLima)
                    .state_entity_id(stateActive)
                    .build();
            distMiraflores = districtRepository.save(distMiraflores);

            District distSanIsidro = District.builder()
                    .district_name("San Isidro")
                    .province_id(provLima)
                    .state_entity_id(stateActive)
                    .build();
            distSanIsidro = districtRepository.save(distSanIsidro);

            District distSurco = District.builder()
                    .district_name("Surco")
                    .province_id(provLima)
                    .state_entity_id(stateActive)
                    .build();
            distSurco = districtRepository.save(distSurco);

            log.info("✅ Ubicaciones creadas: Lima > Lima > Miraflores, San Isidro, Surco");

            // ========== 8. CLIENTES ==========
            log.info("👤 Creando clientes de prueba...");

            Client client1 = Client.builder()
                    .client_name("Juan")
                    .client_last_name("Pérez García")
                    .registration_date(java.time.LocalDateTime.now())
                    .client_type_id(typeRegular)
                    .identification_id(id1)
                    .state_entity_id(stateActive)
                    .build();
            client1 = clientRepository.save(client1);

            Client client2 = Client.builder()
                    .client_name("María")
                    .client_last_name("López Fernández")
                    .registration_date(java.time.LocalDateTime.now())
                    .client_type_id(typePremium)
                    .identification_id(id2)
                    .state_entity_id(stateActive)
                    .build();
            client2 = clientRepository.save(client2);

            Client client3 = Client.builder()
                    .client_name("Empresa XYZ")
                    .client_last_name("S.A.C.")
                    .registration_date(java.time.LocalDateTime.now())
                    .client_type_id(typeVIP)
                    .identification_id(id3)
                    .state_entity_id(stateActive)
                    .build();
            client3 = clientRepository.save(client3);

            log.info("✅ Clientes creados: {} {} (ID: {}), {} {} (ID: {}), {} {} (ID: {})",
                    client1.getClient_name(), client1.getClient_last_name(), client1.getClient_id(),
                    client2.getClient_name(), client2.getClient_last_name(), client2.getClient_id(),
                    client3.getClient_name(), client3.getClient_last_name(), client3.getClient_id());

            // ========== 9. CONTACTOS ==========
            log.info("📞 Creando contactos de prueba...");

            Contact contact1 = Contact.builder()
                    .client_id(client1)
                    .phone_number("987654321")
                    .email("juan.perez@email.com")
                    .state_entity_id(stateActive)
                    .build();
            contact1 = contactRepository.save(contact1);

            Contact contact2 = Contact.builder()
                    .client_id(client2)
                    .phone_number("912345678")
                    .email("maria.lopez@email.com")
                    .state_entity_id(stateActive)
                    .build();
            contact2 = contactRepository.save(contact2);

            Contact contact3 = Contact.builder()
                    .client_id(client3)
                    .phone_number("(01) 234-5678")
                    .email("contacto@empresaxyz.com")
                    .state_entity_id(stateActive)
                    .build();
            contact3 = contactRepository.save(contact3);

            log.info("✅ Contactos creados: {} ({}), {} ({}), {} ({})",
                    contact1.getEmail(), contact1.getContact_id(),
                    contact2.getEmail(), contact2.getContact_id(),
                    contact3.getEmail(), contact3.getContact_id());

            // ========== 10. DIRECCIONES ==========
            log.info("🏡 Creando direcciones de prueba...");

            Direction dir1 = Direction.builder()
                    .client_id(client1)
                    .direction_type_id(typeCasa)
                    .direction_name("Av. Larco")
                    .direction_number("1234")
                    .reference("Cerca al Parque Kennedy")
                    .district_id(distMiraflores)
                    .state_entity_id(stateActive)
                    .build();
            dir1 = directionRepository.save(dir1);

            Direction dir2 = Direction.builder()
                    .client_id(client2)
                    .direction_type_id(typeCasa)
                    .direction_name("Calle Las Begonias")
                    .direction_number("567")
                    .reference("Edificio azul, tercer piso")
                    .district_id(distSanIsidro)
                    .state_entity_id(stateActive)
                    .build();
            dir2 = directionRepository.save(dir2);

            Direction dir3 = Direction.builder()
                    .client_id(client3)
                    .direction_type_id(typeOficina)
                    .direction_name("Av. Primavera")
                    .direction_number("890")
                    .reference("Centro Empresarial, Torre B")
                    .district_id(distSurco)
                    .state_entity_id(stateActive)
                    .build();
            dir3 = directionRepository.save(dir3);

            log.info("✅ Direcciones creadas: {} {} (ID: {}), {} {} (ID: {}), {} {} (ID: {})",
                    dir1.getDirection_name(), dir1.getDirection_number(), dir1.getDirection_id(),
                    dir2.getDirection_name(), dir2.getDirection_number(), dir2.getDirection_id(),
                    dir3.getDirection_name(), dir3.getDirection_number(), dir3.getDirection_id());

            log.info("🎉 ¡Carga de datos completada exitosamente!");
            log.info("📊 Resumen:");
            log.info("   - {} Estados", stateEntityRepository.count());
            log.info("   - {} Tipos de Persona", personTypeRepository.count());
            log.info("   - {} Tipos de Identificación", identificationTypeRepository.count());
            log.info("   - {} Identificaciones", identificationRepository.count());
            log.info("   - {} Tipos de Cliente", clientTypeRepository.count());
            log.info("   - {} Tipos de Dirección", directionTypeRepository.count());
            log.info("   - {} Departamentos", departmentRepository.count());
            log.info("   - {} Provincias", provinceRepository.count());
            log.info("   - {} Distritos", districtRepository.count());
            log.info("   - {} Clientes", clientRepository.count());
            log.info("   - {} Contactos", contactRepository.count());
            log.info("   - {} Direcciones", directionRepository.count());
        };
    }
}

