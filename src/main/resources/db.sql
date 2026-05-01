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
    stock INT NOT NULL DEFAULT 0,
    id_categoria INT NOT NULL,
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