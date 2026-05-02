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
}
