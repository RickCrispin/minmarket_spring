package com.example.demo.repositories.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Categoria;
import com.example.demo.repositories.CategoriaDAO;

@Repository
public class CategoriaRepository implements CategoriaDAO {
    private final JdbcTemplate jdbcTemplate;
    public CategoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Categoria> rowMapper = (rs, rowNum) -> {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("id"));
        categoria.setNombre(rs.getString("nombre_categoria"));
        categoria.setDescripcion(rs.getString("descripcion"));
        categoria.setEstado(rs.getString("estado"));
        return categoria;
    };

    public List<Categoria> getAllCategorias() {
        String sql = "SELECT * FROM categorias";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Categoria getCategoriaById(int id) {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void addCategoria(Categoria categoria) {
        String sql = "INSERT INTO categorias (nombre_categoria, descripcion, estado) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, categoria.getNombre(), categoria.getDescripcion(), categoria.getEstado());
    }

    public void updateCategoria(Categoria categoria) {
        String sql = "UPDATE categorias SET nombre_categoria = ?, descripcion = ?, estado = ? WHERE id = ?";
        jdbcTemplate.update(sql, categoria.getNombre(), categoria.getDescripcion(), categoria.getEstado(), categoria.getId());
        syncProductsEstadoByCategory(categoria.getId(), categoria.getEstado());
    }

    public void desactivarCategoria(int id) {
        String sql = "UPDATE categorias SET estado = 'Inactivo' WHERE id = ?";
        jdbcTemplate.update(sql, id);
        syncProductsEstadoByCategory(id, "Inactivo");
    }

    private void syncProductsEstadoByCategory(int categoriaId, String estado) {
        String sql = "UPDATE productos SET estado = ? WHERE id_categoria = ?";
        jdbcTemplate.update(sql, estado, categoriaId);
    }
}
