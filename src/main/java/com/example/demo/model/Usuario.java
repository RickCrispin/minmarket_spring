package com.example.demo.model;

import java.time.LocalDateTime;

public class Usuario {
    private Integer id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String password;
    private String telefono;
    private String direccion;
    private LocalDateTime fecha;

    
    public Usuario(){
        
    }
    public Usuario(Integer id, 
        String nombres, 
        String apellidos, 
        String correo,
        String password,
        String telefono,
        String direccion,
        LocalDateTime fecha){
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha = fecha;
    }
    
        public Integer getId() {
            return this.id;
        }
    
        public void setId(Integer id) {
            this.id = id;
        }
    
        public String getNombres() {
            return this.nombres;
        }
    
        public void setNombres(String nombres) {
            this.nombres = nombres;
        }
    
        public String getApellidos() {
            return this.apellidos;
        }
    
        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }
    
        public String getCorreo() {
            return this.correo;
        }
    
        public void setCorreo(String correo) {
            this.correo = correo;
        }
    
        public String getPassword() {
            return this.password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
    
        public String getTelefono() {
            return this.telefono;
        }
    
        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
    
        public String getDireccion() {
            return this.direccion;
        }
    
        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }
    
        public LocalDateTime getFecha() {
            return this.fecha;
        }
    
        public void setFecha(LocalDateTime fecha) {
            this.fecha = fecha;
        }
}
