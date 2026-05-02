package com.example.demo.repositories.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DetalleVenta;
import com.example.demo.model.Producto;
import com.example.demo.repositories.DetalleVentaDAO;

@Repository
public class DetalleVentaRepository implements DetalleVentaDAO {
    private final JdbcTemplate jdbcTemplate;

    public DetalleVentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<DetalleVenta> rowMapper = (rs, rowNum) -> {
        Producto producto = new Producto(
                rs.getInt("id_producto"),
                rs.getString("nombre_producto"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                rs.getInt("id_categoria"),
                null,
                rs.getTimestamp("fecha_registro") != null ? rs.getTimestamp("fecha_registro").toLocalDateTime() : null
        );

        return new DetalleVenta(
                rs.getInt("id"),
                rs.getInt("id_venta"),
                rs.getInt("id_producto"),
                rs.getInt("cantidad"),
                rs.getDouble("precio_unitario"),
                rs.getDouble("subtotal"),
                producto
        );
    };

    @Override
    public void addDetalleVenta(DetalleVenta detalleVenta) {
        String sql = "INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                detalleVenta.getIdVenta(),
                detalleVenta.getIdProducto(),
                detalleVenta.getCantidad(),
                detalleVenta.getPrecioUnitario());
    }

    @Override
    public List<DetalleVenta> getDetallesByVentaId(int idVenta) {
        String sql = "SELECT dv.id, dv.id_venta, dv.id_producto, dv.cantidad, dv.precio_unitario, dv.subtotal, " +
                "p.nombre_producto, p.descripcion, p.precio, p.stock, p.id_categoria, p.fecha_registro " +
                "FROM detalle_venta dv " +
                "INNER JOIN productos p ON dv.id_producto = p.id " +
                "WHERE dv.id_venta = ? " +
                "ORDER BY dv.id ASC";
        return jdbcTemplate.query(sql, rowMapper, idVenta);
    }
}
