package com.example.demo.repositories.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Venta;
import com.example.demo.repositories.VentaDAO;

@Repository
public class VentaRepository implements VentaDAO {
    private final JdbcTemplate jdbcTemplate;

    public VentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Venta> rowMapper = (rs, rowNum) -> {
        Venta venta = new Venta();
        venta.setId(rs.getInt("id"));
        venta.setIdUsuario(rs.getInt("id_usuario"));
        Timestamp timestamp = rs.getTimestamp("fecha_venta");
        if (timestamp != null) {
            venta.setFechaVenta(timestamp.toLocalDateTime());
        }
        venta.setTotal(rs.getDouble("total"));
        venta.setEstado(rs.getString("estado"));
        return venta;
    };

    @Override
    public Integer createVenta(Integer idUsuario) {
        String sql = "INSERT INTO ventas (id_usuario, estado) VALUES (?, 'Pendiente')";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idUsuario);
            return ps;
        }, keyHolder);
        Number key = keyHolder.getKey();
        return key != null ? key.intValue() : null;
    }

    @Override
    public Venta getVentaById(int idVenta) {
        String sql = "SELECT * FROM ventas WHERE id = ?";
        List<Venta> ventas = jdbcTemplate.query(sql, rowMapper, idVenta);
        return ventas.isEmpty() ? null : ventas.get(0);
    }

    @Override
    public java.util.List<Venta> getAllVentas() {
        String sql = "SELECT * FROM ventas ORDER BY id DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void updateTotal(int idVenta, Double total) {
        String sql = "UPDATE ventas SET total = ? WHERE id = ?";
        jdbcTemplate.update(sql, total, idVenta);
    }

    @Override
    public void updateEstado(int idVenta, String estado) {
        String sql = "UPDATE ventas SET estado = ? WHERE id = ?";
        jdbcTemplate.update(sql, estado, idVenta);
    }

    @Override
    public void deleteVenta(int idVenta) {
        String sql = "DELETE FROM ventas WHERE id = ?";
        jdbcTemplate.update(sql, idVenta);
    }
}
