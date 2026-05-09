-- ==========================================
-- SCRIPT MYSQL - SISTEMA DE VENTAS
-- Compatible con MySQL 8+
-- ==========================================

DROP DATABASE IF EXISTS minimarket_ventas;
CREATE DATABASE minimarket_ventas;
USE minimarket_ventas;

-- ==========================================
-- TABLA: USUARIOS
-- ==========================================

CREATE TABLE usuarios (
    id INT NOT NULL AUTO_INCREMENT,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(200),
    estado ENUM('Activo','Inactivo') NOT NULL DEFAULT 'Activo',
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    UNIQUE KEY uk_correo (correo)
) ENGINE=InnoDB;

-- ==========================================
-- TABLA: CATEGORIAS
-- ==========================================

CREATE TABLE categorias (
    id INT NOT NULL AUTO_INCREMENT,
    nombre_categoria VARCHAR(100) NOT NULL,
    descripcion TEXT,
    estado ENUM('Activo','Inactivo') NOT NULL DEFAULT 'Activo',

    PRIMARY KEY (id),
    UNIQUE KEY uk_nombre_categoria (nombre_categoria)
) ENGINE=InnoDB;

-- ==========================================
-- TABLA: PRODUCTOS
-- ==========================================

CREATE TABLE productos (
    id INT NOT NULL AUTO_INCREMENT,
    nombre_producto VARCHAR(150) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    id_categoria INT NOT NULL,
    estado ENUM('Activo','Inactivo') NOT NULL DEFAULT 'Activo',
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    KEY fk_producto_categoria (id_categoria),

    CONSTRAINT fk_producto_categoria
        FOREIGN KEY (id_categoria)
        REFERENCES categorias(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ==========================================
-- TABLA: VENTAS
-- ==========================================

CREATE TABLE ventas (
    id INT NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    fecha_venta DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    estado ENUM('Pendiente','Concretado') NOT NULL DEFAULT 'Pendiente',

    PRIMARY KEY (id),
    KEY fk_venta_usuario (id_usuario),

    CONSTRAINT fk_venta_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ==========================================
-- TABLA: DETALLE_VENTA
-- ==========================================

CREATE TABLE detalle_venta (
    id INT NOT NULL AUTO_INCREMENT,
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2)
        GENERATED ALWAYS AS (cantidad * precio_unitario) STORED,

    PRIMARY KEY (id),
    KEY fk_detalle_venta (id_venta),
    KEY fk_detalle_producto (id_producto),

    CONSTRAINT fk_detalle_venta
        FOREIGN KEY (id_venta)
        REFERENCES ventas(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    CONSTRAINT fk_detalle_producto
        FOREIGN KEY (id_producto)
        REFERENCES productos(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ==========================================
-- USUARIOS
-- ==========================================

INSERT INTO usuarios
(nombres, apellidos, correo, password, telefono, direccion, estado)
VALUES
('Admin', 'Central', 'admin@minimarket.com', 'admin123', '+51 900 000 001', 'Av. Principal 100', 'Activo'),
('María', 'Gonzales', 'maria@minimarket.com', 'maria123', '+51 900 000 002', 'Jr. Comercio 55', 'Activo');

-- ==========================================
-- CATEGORÍAS
-- ==========================================

INSERT INTO categorias
(nombre_categoria, descripcion, estado)
VALUES
('Bebidas', 'Refrescos, jugos y bebidas', 'Activo'),
('Lácteos', 'Leche, yogurt y derivados', 'Activo'),
('Abarrotes', 'Productos básicos no perecibles', 'Activo'),
('Snacks', 'Aperitivos y dulces', 'Activo'),
('Panadería', 'Productos horneados', 'Activo');

-- ==========================================
-- PRODUCTOS
-- ==========================================

INSERT INTO productos
(nombre_producto, descripcion, precio, id_categoria, estado)
VALUES
('Agua Mineral 500ml', 'Botella individual sin gas', 2.50, 1, 'Activo'),
('Gaseosa Cola 355ml', 'Lata individual', 3.00, 1, 'Activo'),
('Jugo en Caja 1L', 'Bebida lista para consumo', 4.50, 1, 'Activo'),

('Leche UHT 1L', 'Leche larga vida', 5.60, 2, 'Activo'),
('Yogurt Bebible 250ml', 'Presentación individual', 2.80, 2, 'Activo'),

('Fideos Spaghetti 500g', 'Paquete cerrado', 3.00, 3, 'Activo'),
('Arroz Extra 1Kg', 'Bolsa sellada', 4.20, 3, 'Activo'),

('Chocolate Barra 100g', 'Unidad individual', 3.50, 4, 'Activo'),
('Papas Fritas Bolsa 100g', 'Snack en bolsa', 4.00, 4, 'Activo'),

('Pan de Molde', 'Pan en paquete sellado', 6.50, 5, 'Activo');

-- ==========================================
-- VENTAS
-- ==========================================

INSERT INTO ventas
(id, id_usuario, fecha_venta, total, estado)
VALUES

(1, 1, '2025-01-05 10:15:00', 12.50, 'Concretado'),
(2, 2, '2025-01-08 14:20:00', 18.20, 'Concretado'),
(3, 1, '2025-01-12 09:45:00', 9.00, 'Concretado'),
(4, 2, '2025-01-18 17:10:00', 25.50, 'Concretado'),
(5, 1, '2025-01-25 11:30:00', 7.80, 'Concretado'),

(6, 2, '2025-02-02 13:00:00', 15.60, 'Concretado'),
(7, 1, '2025-02-06 16:25:00', 21.30, 'Concretado'),
(8, 2, '2025-02-10 08:40:00', 13.50, 'Concretado'),
(9, 1, '2025-02-14 19:15:00', 30.00, 'Concretado'),
(10, 2, '2025-02-20 12:50:00', 11.20, 'Concretado'),

(11, 1, '2025-03-01 10:10:00', 19.40, 'Concretado'),
(12, 2, '2025-03-04 15:30:00', 8.50, 'Concretado'),
(13, 1, '2025-03-09 09:00:00', 14.80, 'Concretado'),
(14, 2, '2025-03-15 18:45:00', 22.60, 'Concretado'),
(15, 1, '2025-03-22 11:20:00', 17.00, 'Concretado'),

(16, 2, '2026-01-03 13:15:00', 26.40, 'Concretado'),
(17, 1, '2026-01-06 16:40:00', 9.50, 'Concretado'),
(18, 2, '2026-01-11 08:30:00', 12.00, 'Concretado'),
(19, 1, '2026-02-16 20:10:00', 28.30, 'Concretado'),
(20, 2, '2026-02-21 12:00:00', 6.50, 'Concretado'),

(21, 1, '2026-03-02 10:50:00', 16.90, 'Concretado'),
(22, 2, '2026-03-05 14:35:00', 24.70, 'Concretado'),
(23, 1, '2026-03-09 09:25:00', 10.20, 'Concretado'),
(24, 2, '2026-03-13 18:00:00', 31.50, 'Concretado'),
(25, 1, '2026-03-18 11:10:00', 8.00, 'Concretado'),

(26, 2, '2026-04-01 13:45:00', 20.60, 'Concretado'),
(27, 1, '2026-04-05 15:20:00', 11.50, 'Concretado'),
(28, 2, '2026-04-10 08:15:00', 27.80, 'Concretado'),
(29, 1, '2026-04-14 19:40:00', 14.30, 'Concretado'),
(30, 2, '2026-05-04 12:25:00', 18.90, 'Concretado');

-- ==========================================
-- DETALLE DE VENTAS
-- ==========================================

INSERT INTO detalle_venta
(id_venta, id_producto, cantidad, precio_unitario)
VALUES

(1, 1, 2, 2.50),
(1, 8, 1, 3.50),

(2, 4, 2, 5.60),
(2, 5, 1, 2.80),

(3, 2, 3, 3.00),

(4, 7, 3, 4.20),
(4, 10, 2, 6.50),

(5, 1, 1, 2.50),
(5, 5, 1, 2.80),

(6, 6, 2, 3.00),
(6, 2, 2, 3.00),

(7, 3, 3, 4.50),

(8, 4, 2, 5.60),
(8, 1, 1, 2.50),

(9, 8, 2, 3.50),
(9, 2, 2, 3.00),

(10, 5, 1, 2.80),

(11, 6, 2, 3.00),
(11, 7, 2, 4.20),

(12, 4, 1, 5.60),
(12, 3, 2, 4.50),

(13, 9, 3, 4.00),

(14, 10, 2, 6.50),
(14, 1, 1, 2.50),

(15, 3, 1, 4.50),

(16, 7, 2, 4.20),
(16, 2, 2, 3.00),

(17, 4, 3, 5.60),

(18, 10, 1, 6.50),

(19, 1, 2, 2.50),
(19, 9, 1, 4.00),

(20, 6, 3, 3.00),

(21, 8, 1, 3.50),
(21, 2, 2, 3.00),

(22, 4, 2, 5.60),
(22, 9, 1, 4.00),

(23, 1, 1, 2.50),

(24, 5, 2, 2.80),
(24, 7, 1, 4.20),

(25, 2, 1, 3.00),

(26, 4, 2, 5.60),
(26, 3, 1, 4.50),

(27, 7, 1, 4.20),

(28, 10, 2, 6.50),

(29, 3, 2, 4.50),

(30, 4, 1, 5.60),
(30, 8, 1, 3.50);