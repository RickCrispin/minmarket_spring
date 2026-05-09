package com.example.demo.repositories;

import java.util.List;

import com.example.demo.model.Usuario;


public interface UsuarioDAO {
    Usuario authUsuario(String user, String password);

    List<Usuario> getAllUsuarios(Integer userId);
    Usuario getUsuarioById(int id);
    void addUsuario(Usuario usuario);
    void updateUsuario(Usuario usuario);
    void desactivarUsuario(int id);
}
