-- ===== Schema LogiTrack =====

CREATE DATABASE IF NOT EXISTS logitrack_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE logitrack_db;

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nombre_completo VARCHAR(100) NOT NULL,
    rol ENUM('ADMIN', 'EMPLEADO') NOT NULL DEFAULT 'EMPLEADO'
);

-- Tabla de bodegas
CREATE TABLE IF NOT EXISTS bodegas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(200) NOT NULL,
    capacidad INT NOT NULL,
    encargado VARCHAR(100) NOT NULL
);

-- Tabla de productos
CREATE TABLE IF NOT EXISTS productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    precio DECIMAL(12, 2) NOT NULL
);

-- Tabla de movimientos de inventario
CREATE TABLE IF NOT EXISTS movimientos_inventario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tipo_movimiento ENUM('ENTRADA', 'SALIDA', 'TRANSFERENCIA') NOT NULL,
    usuario_id BIGINT NOT NULL,
    bodega_origen_id BIGINT,
    bodega_destino_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (bodega_origen_id) REFERENCES bodegas(id),
    FOREIGN KEY (bodega_destino_id) REFERENCES bodegas(id)
);

-- Tabla de detalles de movimiento
CREATE TABLE IF NOT EXISTS movimiento_detalles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movimiento_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (movimiento_id) REFERENCES movimientos_inventario(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- Tabla de auditorias
CREATE TABLE IF NOT EXISTS auditorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_operacion ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
    fecha_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario VARCHAR(50) NOT NULL,
    entidad_afectada VARCHAR(100) NOT NULL,
    valores_anteriores TEXT,
    valores_nuevos TEXT
);
