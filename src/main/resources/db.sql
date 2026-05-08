-- ==========================================
-- SCRIPT MYSQL - SISTEMA DE VENTAS
-- Compatible con MySQL 8+
-- ==========================================

CREATE DATABASE IF NOT EXISTS minimarket_ventas;
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
    subtotal DECIMAL(10,2) GENERATED ALWAYS AS (cantidad * precio_unitario) STORED,

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
-- DATOS DE EJEMPLO
-- ==========================================

INSERT INTO usuarios (nombres, apellidos, correo, password, telefono, direccion, estado)
VALUES
    ('Admin', 'Central', 'admin@minimarket.com', 'admin123', '+51 900 000 001', 'Av. Principal 100', 'Activo'),
    ('María', 'Gonzales', 'maria@minimarket.com', 'maria123', '+51 900 000 002', 'Jr. Comercio 55', 'Activo');

-- CATEGORÍAS
INSERT INTO categorias (nombre_categoria, descripcion, estado)
VALUES
    ('Bebidas', 'Refrescos, jugos y bebidas', 'Activo'),
    ('Lácteos', 'Leche, yogurt y derivados', 'Activo'),
    ('Abarrotes', 'Productos básicos no perecibles', 'Activo'),
    ('Snacks', 'Aperitivos y dulces', 'Activo'),
    ('Panadería', 'Productos horneados', 'Activo');

-- PRODUCTOS 
INSERT INTO productos (nombre_producto, descripcion, precio, id_categoria, estado)
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
INSERT INTO ventas (id_usuario, fecha_venta, total, estado)
VALUES
    (1, NOW(), 21.20, 'Concretado'),
    (2, NOW(), 5.50, 'Pendiente');

INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario)
VALUES
    (1, 1, 2, 5.50),
    (1, 2, 1, 3.20),
    (1, 3, 1, 12.00),
    (2, 1, 1, 5.50);
