
package com.mycompany.quizregistropersona;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Registro {
    
    private static final String NOMBRE_ARCHIVO = "personas.txt";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final Scanner scanner;
    private Object bw;

    public Registro(Scanner scanner) {
        this.scanner = scanner;
    }

    public void registrarPersona() {
        
        String nombre = leerNoVacio(scanner, 
                "Ingrese Nombre Completo: ");
        String id = leerNoVacio(scanner, 
                "Ingrese Identificacion (ID/Cedula): ");
        int edad = leerEnteroPositivo(scanner, 
                "Ingrese Edad: ");
        String correo = leerEmailValido(scanner, 
                "Ingrese Correo Electronico (debe contener '@'): ");
        String telefono = leerTelefonoValido(scanner, 
                "Ingrese Telefono (solo digitos, minimo 8 caracteres): ");

        //linea y fecha:
        String fechaRegistro = LocalDateTime.now().format(FORMATO_FECHA);
        String linea = String.format("%s|%s|%d|%s|%s|%s", nombre, id, edad, correo, telefono, fechaRegistro);

        //guardarlo en archivo:
        try {
            guardarLinea(linea);
            System.out.println("Registro guardado en " + NOMBRE_ARCHIVO);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public String leerNoVacio(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("ERROR: Este campo no puede estar vacío.");
            }
        } while (input.isEmpty());
        return input;
    }

    public int leerEnteroPositivo(Scanner scanner, String prompt) {
        int entero = -1;
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            try {
                entero = Integer.parseInt(input);
                if (entero <= 0) {
                    System.out.println("ERROR: La edad debe ser un numero entero positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Por favor, ingrese un numero entero válido.");
                entero = -1;
            }
        } while (entero <= 0);
        return entero;
    }

    public String leerEmailValido(Scanner scanner, String prompt) {
        String input;
        do {
            input = leerNoVacio(scanner, prompt);
            if (!input.contains("@")) {
                System.out.println("ERROR: El correo debe contener el simbolo '@'.");
            }
        } while (!input.contains("@"));
        return input;
    }

    public String leerTelefonoValido(Scanner scanner, String prompt) {
        String input;
        do {
            input = leerNoVacio(scanner, prompt); 
            if (!input.matches("\\d+") || input.length() < 8) {
                System.out.println("ERROR: El telefono solo debe contener digitos y tener al menos 8 caracteres.");
            }
        } while (!input.matches("\\d+") || input.length() < 8);
        return input;
    }

    public void guardarLinea(String linea) throws IOException {
        
        try (FileWriter fw = new FileWriter(NOMBRE_ARCHIVO, true); 
             BufferedWriter bw = new BufferedWriter(fw)) {
             
            bw.write(linea);
            bw.newLine();
            }
    }
    
}
