
package com.mycompany.quizregistropersona;

import java.util.Scanner;


public class QuizRegistroPersona {

    public static void main(String[] args) {
        System.out.println("--- Sistema de Registro de Datos Personales ---");

        try (Scanner scanner = new Scanner(System.in)) {
            Registro persona = new Registro(scanner);
            persona.registrarPersona();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error inesperado en la aplicacion: " + e.getMessage());
        } finally {
            System.out.println("--- Fin del Registro ---");
        }
    }
}
