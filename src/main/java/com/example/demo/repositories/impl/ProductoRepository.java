package com.example.demo.repositories.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Categoria;
import com.example.demo.model.Producto;
import com.example.demo.repositories.ProductoDAO;

@Repository
public class ProductoRepository implements ProductoDAO {
    private final JdbcTemplate jdbcTemplate;

    public ProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Producto> rowMapper = (rs, rowNum) -> {
        Producto producto = new Producto();
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre_producto"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setStock(rs.getInt("stock"));
        producto.setIdCategoria(rs.getInt("id_categoria"));
        producto.setFechaRegistro(rs.getTimestamp("fecha_registro") != null ? 
            rs.getTimestamp("fecha_registro").toLocalDateTime() : null);
        
        // Cargar la categoría relacionada
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("id_categoria"));
        categoria.setNombre(rs.getString("nombre_categoria"));
        categoria.setDescripcion(rs.getString("cat_descripcion"));
        categoria.setEstado(rs.getString("cat_estado"));
        producto.setCategoria(categoria);
        
        return producto;
    };

    @Override
    public List<Producto> getAllProductos() {
        String sql = "SELECT p.*, c.nombre_categoria, c.descripcion as cat_descripcion, c.estado as cat_estado " +
                     "FROM productos p " +
                     "LEFT JOIN categorias c ON p.id_categoria = c.id " +
                     "WHERE p.estado = 'Activo' " +
                     "ORDER BY p.id DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Producto getProductoById(int id) {
        String sql = "SELECT p.*, c.nombre_categoria, c.descripcion as cat_descripcion, c.estado as cat_estado " +
                     "FROM productos p " +
                     "LEFT JOIN categorias c ON p.id_categoria = c.id " +
                     "WHERE p.id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public void addProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre_producto, descripcion, precio, stock, id_categoria, estado) " +
                     "VALUES (?, ?, ?, ?, ?, 'Activo')";
        jdbcTemplate.update(sql, 
            producto.getNombre(), 
            producto.getDescripcion(), 
            producto.getPrecio(), 
            producto.getStock(), 
            producto.getIdCategoria());
    }

    @Override
    public void updateProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre_producto = ?, descripcion = ?, precio = ?, " +
                     "stock = ?, id_categoria = ? WHERE id = ?";
        jdbcTemplate.update(sql, 
            producto.getNombre(), 
            producto.getDescripcion(), 
            producto.getPrecio(), 
            producto.getStock(), 
            producto.getIdCategoria(), 
            producto.getId());
    }

    @Override
    public void desactivarProducto(int id) {
        String sql = "UPDATE productos SET estado = 'Inactivo' WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
