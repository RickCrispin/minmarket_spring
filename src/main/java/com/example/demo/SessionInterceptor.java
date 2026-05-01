package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();

        if(uri.contains("/login") || uri.contains("/css") || uri.contains("/img")) {
            return true; // Permitir acceso a login, recursos públicos, CSS y JS
        }

        if (session != null && session.getAttribute("userLogged") != null) {
            return true; // Usuario autenticado, permitir acceso
        }

        response.sendRedirect("/login"); // Redirigir a login si no está autenticado
        return false;
    }

}
