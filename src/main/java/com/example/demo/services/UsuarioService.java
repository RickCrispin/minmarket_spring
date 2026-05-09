package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Usuario;

public interface UsuarioService {
    List<Usuario> getAllUsuarios(Integer userId);
    Usuario getUsuarioById(int id);
    void addUsuario(Usuario usuario);
    void updateUsuario(Usuario usuario);
    void desactivarUsuario(int id);
    Usuario authUsuario(String user, String password);
}
