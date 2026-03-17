-- ===== Datos iniciales LogiTrack =====

USE logitrack_db;

-- Usuario admin (password: admin123 encriptado con BCrypt)
INSERT IGNORE INTO usuarios (username, password, nombre_completo, rol) VALUES
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Administrador General', 'ADMIN'),
('empleado1', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Carlos Ramirez', 'EMPLEADO'),
('empleado2', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Maria Lopez', 'EMPLEADO');

-- Bodegas
INSERT IGNORE INTO bodegas (nombre, ubicacion, capacidad, encargado) VALUES
('Bodega Central', 'Bogota', 1000, 'Carlos Ramirez'),
('Bodega Norte', 'Medellin', 500, 'Maria Lopez'),
('Bodega Sur', 'Cali', 750, 'Pedro Garcia');

-- Productos
INSERT IGNORE INTO productos (nombre, categoria, stock, precio) VALUES
('Laptop HP 15', 'Electronica', 50, 2500000.00),
('Monitor Samsung 24', 'Electronica', 30, 850000.00),
('Teclado Mecanico', 'Accesorios', 100, 250000.00),
('Mouse Inalambrico', 'Accesorios', 150, 85000.00),
('Silla Ergonomica', 'Mobiliario', 8, 950000.00),
('Escritorio Ajustable', 'Mobiliario', 5, 1200000.00),
('Cable HDMI 2m', 'Accesorios', 200, 35000.00),
('Impresora Epson', 'Electronica', 15, 680000.00),
('Papel Resma A4', 'Papeleria', 300, 15000.00),
('Cinta Adhesiva', 'Papeleria', 7, 5000.00);
