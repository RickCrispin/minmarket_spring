package com.example.demo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repositories.UsuarioDAO;
import com.example.demo.services.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioDAO usuarioDAO;

    public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public List<Usuario> getAllUsuarios(Integer userId) {
        return usuarioDAO.getAllUsuarios(userId);
    }

    @Override
    public Usuario getUsuarioById(int id) {
        return usuarioDAO.getUsuarioById(id);
    }

    @Override
    public void addUsuario(Usuario usuario) {
        usuarioDAO.addUsuario(usuario);
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        usuarioDAO.updateUsuario(usuario);
    }

    @Override
    public void desactivarUsuario(int id) {
        usuarioDAO.desactivarUsuario(id);
    }

    @Override
    public Usuario authUsuario(String user, String password) {
        return usuarioDAO.authUsuario(user, password);
    }
}
