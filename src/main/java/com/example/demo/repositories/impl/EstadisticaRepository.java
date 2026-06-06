package com.example.demo.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductoVendido;
import com.example.demo.model.VentaMensual;
import com.example.demo.model.DiaVenta;
import com.example.demo.model.TicketPromedioMensual;
import com.example.demo.model.CategoriaIngresos;
import com.example.demo.repositories.EstadisticaDAO;

@Repository
public class EstadisticaRepository implements EstadisticaDAO {
    private final JdbcTemplate jdbcTemplate;

    public EstadisticaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ProductoVendido> productoRowMapper = (rs, rowNum) -> mapProductoVendido(rs);
    private final RowMapper<VentaMensual> ventaMensualRowMapper = (rs, rowNum) -> mapVentaMensual(rs);
    private final RowMapper<DiaVenta> diaVentaRowMapper = (rs, rowNum) -> mapDiaVenta(rs);
    private final RowMapper<TicketPromedioMensual> ticketPromedioRowMapper = (rs, rowNum) -> mapTicketPromedio(rs);
    private final RowMapper<CategoriaIngresos> categoriaIngresosRowMapper = (rs, rowNum) -> mapCategoriaIngresos(rs);
    private final RowMapper<VentaMensual> ventaMensualGenericRowMapper = (rs, rowNum) -> mapVentaMensual(rs);

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
        return jdbcTemplate.query(sql, Objects.requireNonNull(productoRowMapper), limit);
    }

    @Override
    public java.util.List<VentaMensual> getVentasMensuales(int monthsBack) {
        String sql = "SELECT YEAR(v.fecha_venta) AS ano, MONTH(v.fecha_venta) AS mes, SUM(v.total) AS total "
                + "FROM ventas v "
                + "WHERE v.estado = 'Concretado' AND v.fecha_venta >= DATE_SUB(CURDATE(), INTERVAL ? MONTH) "
                + "GROUP BY YEAR(v.fecha_venta), MONTH(v.fecha_venta) "
                + "ORDER BY YEAR(v.fecha_venta) ASC, MONTH(v.fecha_venta) ASC";
        return jdbcTemplate.query(sql, Objects.requireNonNull(ventaMensualRowMapper), monthsBack);
    }

    @Override
    public java.util.List<DiaVenta> getVentasPorDiaSemana(int monthsBack) {
        String sql = "SELECT DAYNAME(v.fecha_venta) AS dia_nombre, SUM(v.total) AS total "
                + "FROM ventas v "
                + "WHERE v.estado = 'Concretado' AND v.fecha_venta >= DATE_SUB(CURDATE(), INTERVAL ? MONTH) "
                + "GROUP BY DAYOFWEEK(v.fecha_venta), DAYNAME(v.fecha_venta) "
                + "ORDER BY DAYOFWEEK(v.fecha_venta) ASC";
        return jdbcTemplate.query(sql, Objects.requireNonNull(diaVentaRowMapper), monthsBack);
    }

    @Override
    public java.util.List<TicketPromedioMensual> getTicketPromedioMensual(int monthsBack) {
        String sql = "SELECT YEAR(v.fecha_venta) AS ano, MONTH(v.fecha_venta) AS mes, AVG(v.total) AS promedio "
                + "FROM ventas v "
                + "WHERE v.estado = 'Concretado' AND v.fecha_venta >= DATE_SUB(CURDATE(), INTERVAL ? MONTH) "
                + "GROUP BY YEAR(v.fecha_venta), MONTH(v.fecha_venta) "
                + "ORDER BY YEAR(v.fecha_venta) ASC, MONTH(v.fecha_venta) ASC";
        return jdbcTemplate.query(sql, Objects.requireNonNull(ticketPromedioRowMapper), monthsBack);
    }

    @Override
    public java.util.List<CategoriaIngresos> getTopCategoriasPorIngresos(int monthsBack, int limit) {
        String sql = "SELECT c.id AS id_categoria, c.nombre_categoria AS categoria, SUM(d.subtotal) AS total_ingresos "
                + "FROM detalle_venta d "
                + "JOIN productos p ON d.id_producto = p.id "
                + "JOIN categorias c ON p.id_categoria = c.id "
                + "JOIN ventas v ON d.id_venta = v.id "
                + "WHERE v.estado = 'Concretado' AND v.fecha_venta >= DATE_SUB(CURDATE(), INTERVAL ? MONTH) "
                + "GROUP BY c.id, c.nombre_categoria "
                + "ORDER BY SUM(d.subtotal) DESC "
                + "LIMIT ?";
        return jdbcTemplate.query(sql, Objects.requireNonNull(categoriaIngresosRowMapper), monthsBack, limit);
    }

    @Override
    public java.util.List<ProductoVendido> getProductosMenosVendidos(int limit) {
        String sql = "SELECT p.id AS id_producto, p.nombre_producto AS nombre, SUM(d.cantidad) AS total_cantidad, SUM(d.subtotal) AS total_ingresos "
                + "FROM detalle_venta d "
                + "JOIN productos p ON d.id_producto = p.id "
                + "JOIN ventas v ON d.id_venta = v.id "
                + "WHERE v.estado = 'Concretado' "
                + "GROUP BY p.id, p.nombre_producto "
                + "ORDER BY SUM(d.cantidad) ASC "
                + "LIMIT ?";
        return jdbcTemplate.query(sql, Objects.requireNonNull(productoRowMapper), limit);
    }

    @Override
    public java.util.List<VentaMensual> getVentasPromedioDiarioMensual(int monthsBack) {
        String sql = "SELECT YEAR(v.fecha_venta) AS ano, MONTH(v.fecha_venta) AS mes, SUM(v.total) / COUNT(DISTINCT DATE(v.fecha_venta)) AS total "
                + "FROM ventas v "
                + "WHERE v.estado = 'Concretado' AND v.fecha_venta >= DATE_SUB(CURDATE(), INTERVAL ? MONTH) "
                + "GROUP BY YEAR(v.fecha_venta), MONTH(v.fecha_venta) "
                + "ORDER BY YEAR(v.fecha_venta) ASC, MONTH(v.fecha_venta) ASC";
        return jdbcTemplate.query(sql, Objects.requireNonNull(ventaMensualGenericRowMapper), monthsBack);
    }

    @Override
    public java.util.List<VentaMensual> getVentasMensualesPorCategoria(int monthsBack, int categoriaId) {
        String sql = "SELECT YEAR(v.fecha_venta) AS ano, MONTH(v.fecha_venta) AS mes, SUM(d.subtotal) AS total "
                + "FROM detalle_venta d "
                + "JOIN productos p ON d.id_producto = p.id "
                + "JOIN categorias c ON p.id_categoria = c.id "
                + "JOIN ventas v ON d.id_venta = v.id "
                + "WHERE v.estado = 'Concretado' AND c.id = ? AND v.fecha_venta >= DATE_SUB(CURDATE(), INTERVAL ? MONTH) "
                + "GROUP BY YEAR(v.fecha_venta), MONTH(v.fecha_venta) "
                + "ORDER BY YEAR(v.fecha_venta) ASC, MONTH(v.fecha_venta) ASC";
        return jdbcTemplate.query(sql, Objects.requireNonNull(ventaMensualGenericRowMapper), categoriaId, monthsBack);
    }

    @Override
    public java.util.List<VentaMensual> getCantidadMensualPorProducto(int monthsBack, int productoId) {
        String sql = "SELECT YEAR(v.fecha_venta) AS ano, MONTH(v.fecha_venta) AS mes, SUM(d.cantidad) AS total "
                + "FROM detalle_venta d "
                + "JOIN ventas v ON d.id_venta = v.id "
                + "WHERE v.estado = 'Concretado' AND d.id_producto = ? AND v.fecha_venta >= DATE_SUB(CURDATE(), INTERVAL ? MONTH) "
                + "GROUP BY YEAR(v.fecha_venta), MONTH(v.fecha_venta) "
                + "ORDER BY YEAR(v.fecha_venta) ASC, MONTH(v.fecha_venta) ASC";
        return jdbcTemplate.query(sql, Objects.requireNonNull(ventaMensualGenericRowMapper), productoId, monthsBack);
    }

    private ProductoVendido mapProductoVendido(ResultSet rs) throws SQLException {
        ProductoVendido p = new ProductoVendido();
        p.setIdProducto(rs.getInt("id_producto"));
        p.setNombre(rs.getString("nombre"));
        p.setTotalCantidad(rs.getInt("total_cantidad"));
        p.setTotalIngresos(rs.getDouble("total_ingresos"));
        return p;
    }

    private DiaVenta mapDiaVenta(ResultSet rs) throws SQLException {
        DiaVenta d = new DiaVenta();
        d.setNombreDia(rs.getString("dia_nombre"));
        d.setTotal(rs.getDouble("total"));
        return d;
    }

    private TicketPromedioMensual mapTicketPromedio(ResultSet rs) throws SQLException {
        TicketPromedioMensual t = new TicketPromedioMensual();
        t.setAno(rs.getInt("ano"));
        t.setMes(rs.getInt("mes"));
        t.setPromedio(rs.getDouble("promedio"));
        return t;
    }

    private CategoriaIngresos mapCategoriaIngresos(ResultSet rs) throws SQLException {
        CategoriaIngresos c = new CategoriaIngresos();
        c.setIdCategoria(rs.getInt("id_categoria"));
        c.setNombreCategoria(rs.getString("categoria"));
        c.setTotalIngresos(rs.getDouble("total_ingresos"));
        return c;
    }

    private VentaMensual mapVentaMensual(ResultSet rs) throws SQLException {
        VentaMensual v = new VentaMensual();
        v.setAno(rs.getInt("ano"));
        v.setMes(rs.getInt("mes"));
        v.setTotal(rs.getDouble("total"));
        return v;
    }
}
