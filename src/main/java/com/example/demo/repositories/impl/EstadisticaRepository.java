package com.example.demo.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductoVendido;
import com.example.demo.model.VentaMensual;
import com.example.demo.repositories.EstadisticaDAO;

@Repository
public class EstadisticaRepository implements EstadisticaDAO {
    private final JdbcTemplate jdbcTemplate;

    public EstadisticaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ProductoVendido> productoRowMapper = (rs, rowNum) -> mapProductoVendido(rs);
    private final RowMapper<VentaMensual> ventaMensualRowMapper = (rs, rowNum) -> mapVentaMensual(rs);

    @Override
    public java.util.List<ProductoVendido> getTopProductosVendidos(int limit) {
        String sql = "SELECT p.id AS id_producto, p.nombre_producto AS nombre, SUM(d.cantidad) AS total_cantidad, SUM(d.subtotal) AS total_ingresos "
                + "FROM detalle_venta d "
                + "JOIN productos p ON d.id_producto = p.id "
                + "JOIN ventas v ON d.id_venta = v.id "
                + "WHERE v.estado = 'Concretado' "
                + "GROUP BY p.id, p.nombre_producto "
                + "ORDER BY SUM(d.cantidad) DESC "
                + "LIMIT ?";
        return jdbcTemplate.query(sql, productoRowMapper, limit);
    }

    @Override
    public java.util.List<VentaMensual> getVentasMensuales(int monthsBack) {
        String sql = "SELECT YEAR(v.fecha_venta) AS ano, MONTH(v.fecha_venta) AS mes, SUM(v.total) AS total "
                + "FROM ventas v "
                + "WHERE v.estado = 'Concretado' AND v.fecha_venta >= DATE_SUB(CURDATE(), INTERVAL ? MONTH) "
                + "GROUP BY YEAR(v.fecha_venta), MONTH(v.fecha_venta) "
                + "ORDER BY YEAR(v.fecha_venta) DESC, MONTH(v.fecha_venta) DESC";
        return jdbcTemplate.query(sql, ventaMensualRowMapper, monthsBack);
    }

    private ProductoVendido mapProductoVendido(ResultSet rs) throws SQLException {
        ProductoVendido p = new ProductoVendido();
        p.setIdProducto(rs.getInt("id_producto"));
        p.setNombre(rs.getString("nombre"));
        p.setTotalCantidad(rs.getInt("total_cantidad"));
        p.setTotalIngresos(rs.getDouble("total_ingresos"));
        return p;
    }

    private VentaMensual mapVentaMensual(ResultSet rs) throws SQLException {
        VentaMensual v = new VentaMensual();
        v.setAno(rs.getInt("ano"));
        v.setMes(rs.getInt("mes"));
        v.setTotal(rs.getDouble("total"));
        return v;
    }
}
