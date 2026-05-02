package com.example.demo.repositories.impl;


import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Usuario;
import com.example.demo.repositories.UsuarioDAO;

@Repository
public class UsuarioRepository implements UsuarioDAO{
    private final JdbcTemplate jdbcTemplate;
    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Usuario> rowMapper = (rs, rowNum) -> {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNombres(rs.getString("nombres"));
        usuario.setApellidos(rs.getString("apellidos"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setPassword(rs.getString("password"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setDireccion(rs.getString("direccion"));
        usuario.setEstado(rs.getString("estado"));
        usuario.setFecha(rs.getTimestamp("fecha_registro").toLocalDateTime());
        return usuario;
    };

    public Usuario authUsuario(String user, String password) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND password = ? AND estado = 'Activo'";
        List<Usuario> usuarios = jdbcTemplate.query(sql, rowMapper, user, password);
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }

    public List<Usuario> getAllUsuarios() {
        String sql = "SELECT * FROM usuarios ORDER BY id DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Usuario getUsuarioById(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void addUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombres, apellidos, correo, password, telefono, direccion, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, usuario.getNombres(), usuario.getApellidos(), usuario.getCorreo(), usuario.getPassword(), usuario.getTelefono(), usuario.getDireccion(), usuario.getEstado());
    }

    public void updateUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombres = ?, apellidos = ?, correo = ?, password = ?, telefono = ?, direccion = ?, estado = ? WHERE id = ?";
        jdbcTemplate.update(sql, usuario.getNombres(), usuario.getApellidos(), usuario.getCorreo(), usuario.getPassword(), usuario.getTelefono(), usuario.getDireccion(), usuario.getEstado(), usuario.getId());
    }

    public void desactivarUsuario(int id) {
        String sql = "UPDATE usuarios SET estado = 'Inactivo' WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
