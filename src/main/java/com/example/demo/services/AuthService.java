package com.example.demo.services;

import com.example.demo.model.Usuario;

public interface AuthService {
    Usuario authUsuario(String user, String password);
}
