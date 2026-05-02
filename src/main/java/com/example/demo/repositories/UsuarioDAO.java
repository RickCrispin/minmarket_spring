package com.example.demo.repositories;

import com.example.demo.model.Usuario;

public interface UsuarioDAO {
    Usuario authUsuario(String user, String password);
}
