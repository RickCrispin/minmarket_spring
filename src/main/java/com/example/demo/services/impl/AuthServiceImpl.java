package com.example.demo.services.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repositories.UsuarioDAO;
import com.example.demo.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioDAO usuarioDAO;

    public AuthServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public Usuario authUsuario(String user, String password) {
        return usuarioDAO.authUsuario(user, password);
    }
}
