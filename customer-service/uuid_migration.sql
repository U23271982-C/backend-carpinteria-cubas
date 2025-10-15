-- Script de migración para agregar UUIDs a todas las tablas existentes
-- Ejecutar en tu base de datos ANTES de usar la nueva API

-- 1. Agregar columnas UUID a todas las tablas
ALTER TABLE StateEntity ADD COLUMN uuid VARCHAR(36) UNIQUE;
ALTER TABLE Department ADD COLUMN uuid VARCHAR(36) UNIQUE;
ALTER TABLE Province ADD COLUMN uuid VARCHAR(36) UNIQUE;
ALTER TABLE District ADD COLUMN uuid VARCHAR(36) UNIQUE;
ALTER TABLE ClientType ADD COLUMN uuid VARCHAR(36) UNIQUE;
ALTER TABLE IdentificationType ADD COLUMN uuid VARCHAR(36) UNIQUE;
ALTER TABLE PersonType ADD COLUMN uuid VARCHAR(36) UNIQUE;
ALTER TABLE DirectionType ADD COLUMN uuid VARCHAR(36) UNIQUE;
ALTER TABLE Identification ADD COLUMN uuid VARCHAR(36) UNIQUE;
ALTER TABLE Client ADD COLUMN uuid VARCHAR(36) UNIQUE;
ALTER TABLE Contact ADD COLUMN uuid VARCHAR(36) UNIQUE;
ALTER TABLE Direction ADD COLUMN uuid VARCHAR(36) UNIQUE;

-- 2. Generar UUIDs para registros existentes (ejemplo para MySQL)
UPDATE StateEntity SET uuid = UUID() WHERE uuid IS NULL;
UPDATE Department SET uuid = UUID() WHERE uuid IS NULL;
UPDATE Province SET uuid = UUID() WHERE uuid IS NULL;
UPDATE District SET uuid = UUID() WHERE uuid IS NULL;
UPDATE ClientType SET uuid = UUID() WHERE uuid IS NULL;
UPDATE IdentificationType SET uuid = UUID() WHERE uuid IS NULL;
UPDATE PersonType SET uuid = UUID() WHERE uuid IS NULL;
UPDATE DirectionType SET uuid = UUID() WHERE uuid IS NULL;
UPDATE Identification SET uuid = UUID() WHERE uuid IS NULL;
UPDATE Client SET uuid = UUID() WHERE uuid IS NULL;
UPDATE Contact SET uuid = UUID() WHERE uuid IS NULL;
UPDATE Direction SET uuid = UUID() WHERE uuid IS NULL;

-- 3. Hacer las columnas UUID NOT NULL después de generar los UUIDs
ALTER TABLE StateEntity MODIFY COLUMN uuid VARCHAR(36) NOT NULL;
ALTER TABLE Department MODIFY COLUMN uuid VARCHAR(36) NOT NULL;
ALTER TABLE Province MODIFY COLUMN uuid VARCHAR(36) NOT NULL;
ALTER TABLE District MODIFY COLUMN uuid VARCHAR(36) NOT NULL;
ALTER TABLE ClientType MODIFY COLUMN uuid VARCHAR(36) NOT NULL;
ALTER TABLE IdentificationType MODIFY COLUMN uuid VARCHAR(36) NOT NULL;
ALTER TABLE PersonType MODIFY COLUMN uuid VARCHAR(36) NOT NULL;
ALTER TABLE DirectionType MODIFY COLUMN uuid VARCHAR(36) NOT NULL;
ALTER TABLE Identification MODIFY COLUMN uuid VARCHAR(36) NOT NULL;
ALTER TABLE Client MODIFY COLUMN uuid VARCHAR(36) NOT NULL;
ALTER TABLE Contact MODIFY COLUMN uuid VARCHAR(36) NOT NULL;
ALTER TABLE Direction MODIFY COLUMN uuid VARCHAR(36) NOT NULL;

-- 4. Insertar datos básicos de prueba (si no existen)
INSERT IGNORE INTO StateEntity (state_name, description, is_active, uuid) VALUES
('ACTIVO', 'Estado activo del sistema', true, UUID()),
('INACTIVO', 'Estado inactivo del sistema', false, UUID());

INSERT IGNORE INTO IdentificationType (type_name, abbreviation, description, state_entity_id, uuid) VALUES
('Documento Nacional de Identidad', 'DNI', 'Documento nacional de identidad peruano', 1, UUID()),
('Carnet de Extranjería', 'CE', 'Carnet de extranjería para no residentes', 1, UUID());

INSERT IGNORE INTO PersonType (type_name, description, state_entity_id, uuid) VALUES
('Persona Natural', 'Persona física natural', 1, UUID()),
('Persona Jurídica', 'Empresa o entidad legal', 1, UUID());

INSERT IGNORE INTO ClientType (type_name, description, state_entity_id, uuid) VALUES
('Cliente Individual', 'Cliente persona natural', 1, UUID()),
('Cliente Corporativo', 'Cliente empresa', 1, UUID());

INSERT IGNORE INTO DirectionType (type_name, description, state_entity_id, uuid) VALUES
('Domicilio Principal', 'Dirección de residencia principal', 1, UUID()),
('Oficina', 'Dirección de oficina o trabajo', 1, UUID()),
('Entrega', 'Dirección para entregas', 1, UUID());

-- Datos geográficos de ejemplo para Perú
INSERT IGNORE INTO Department (department_name, department_code, state_entity_id, uuid) VALUES
('Lima', '15', 1, UUID()),
('Arequipa', '04', 1, UUID()),
('Cusco', '08', 1, UUID());

INSERT IGNORE INTO Province (province_name, province_code, department_id, state_entity_id, uuid) VALUES
('Lima', '01', 1, 1, UUID()),
('Callao', '07', 1, 1, UUID()),
('Arequipa', '01', 2, 1, UUID());

INSERT IGNORE INTO District (district_name, district_code, province_id, state_entity_id, uuid) VALUES
('Miraflores', '22', 1, 1, UUID()),
('San Isidro', '27', 1, 1, UUID()),
('Barranco', '04', 1, 1, UUID()),
('Callao', '01', 2, 1, UUID());
