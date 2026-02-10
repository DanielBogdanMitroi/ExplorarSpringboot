-- MySQL Setup Script for Spring Boot CRUD Application
-- Execute este script para configurar la base de datos

-- IMPORTANTE: En un entorno de producción, cambie 'ThePassword' por una contraseña segura
-- y asegúrese de actualizar la variable de entorno MYSQL_PASSWORD en consecuencia

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS db_example;

-- Crear el usuario (cambie 'localhost' por '%' si necesita acceso remoto)
-- ADVERTENCIA: Cambie 'ThePassword' por una contraseña segura
CREATE USER IF NOT EXISTS 'springuser'@'%' IDENTIFIED BY 'ThePassword';

-- Otorgar todos los privilegios al usuario sobre la base de datos
GRANT ALL PRIVILEGES ON db_example.* TO 'springuser'@'%';

-- Aplicar los cambios
FLUSH PRIVILEGES;

-- Verificar que la base de datos fue creada
SHOW DATABASES LIKE 'db_example';

-- Usar la base de datos
USE db_example;

-- Verificar las tablas (estará vacío hasta que ejecute la aplicación Spring Boot)
SHOW TABLES;
