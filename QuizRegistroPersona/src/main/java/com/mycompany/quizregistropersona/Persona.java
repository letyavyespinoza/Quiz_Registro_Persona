package com.mycompany.quizregistropersona;


public class Persona {
    private String nombreCompleto;
    private String id;
    private int edad;
    private String correo;
    private String telefono;

    public Persona(String nombreCompleto, String id, int edad, String correo, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.id = id;
        this.edad = edad;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public String getId() { 
        return id; 
    }
    public int getEdad() { 
        return edad; 
    }
    public String getCorreo() { 
        return correo; 
    }
    public String getTelefono() { 
        return telefono; 
    }
}
