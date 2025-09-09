-- Usuario común para desarrollo
CREATE USER IF NOT EXISTS 'devuser'@'%' IDENTIFIED BY 'devpass';
GRANT ALL PRIVILEGES ON user_db.* TO 'devuser'@'%';
GRANT ALL PRIVILEGES ON order_db.* TO 'devuser'@'%';
GRANT ALL PRIVILEGES ON product_db.* TO 'devuser'@'%';
GRANT ALL PRIVILEGES ON auth_db.* TO 'devuser'@'%';
FLUSH PRIVILEGES;
--Creación de bases de datos
CREATE DATABASE IF NOT EXISTS BDEmployeeCarprentry;
CREATE DATABASE IF NOT EXISTS BDInventoryMatter;
--Confirmacion de inicializacion
SELECT 'Bases de datos clientes creados' AS Status;