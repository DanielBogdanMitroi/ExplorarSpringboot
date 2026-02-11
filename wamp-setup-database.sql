-- ========================================
-- Script de Base de Datos para WAMP/MariaDB
-- Spring Boot CRUD Application
-- ========================================

-- Configuración para asegurar compatibilidad con MariaDB
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS `db_example` 
  DEFAULT CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE `db_example`;

-- ========================================
-- Crear la tabla User (Spring Boot la creará automáticamente con JPA,
-- pero si quieres crearla manualmente, usa este esquema)
-- ========================================

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- Insertar datos de ejemplo (opcional)
-- ========================================

INSERT INTO `user` (`name`, `email`) VALUES
('Juan Pérez', 'juan.perez@example.com'),
('María García', 'maria.garcia@example.com'),
('Carlos López', 'carlos.lopez@example.com'),
('Ana Martínez', 'ana.martinez@example.com'),
('Luis Rodríguez', 'luis.rodriguez@example.com')
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- ========================================
-- Verificar la creación
-- ========================================

SHOW TABLES;
SELECT * FROM `user`;
