package main;

import tda.implementaciones.AVL;
import tda.interfaces.AVLTDA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        AVLTDA<Integer> arbol = new AVL<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("--- Creando Arbol AVL (Ejercicio 5) ---");
        System.out.println("La altura inicial del arbol es: " + arbol.getAltura()); // Prueba inicial

        arbol.insertar(10);
        System.out.println("Insertado 10. Altura actual: " + arbol.getAltura());

        arbol.insertar(20);
        System.out.println("Insertado 20. Altura actual: " + arbol.getAltura());

        arbol.insertar(30);
        System.out.println("Insertado 30 (requiere rotacion RR). Altura actual: " + arbol.getAltura());

        arbol.insertar(5);
        System.out.println("Insertado 5. Altura actual: " + arbol.getAltura());

        arbol.insertar(2);
        System.out.println("Insertado 2 (requiere rotacion LL). Altura actual: " + arbol.getAltura());

        arbol.insertar(15);
        System.out.println("Insertado 15. Altura actual: " + arbol.getAltura());

        arbol.insertar(18);
        System.out.println("Insertado 18 (requiere rotacion RL). Altura actual: " + arbol.getAltura());

        System.out.println("\n--- Prueba de Altura Final ---");
        System.out.println("La altura final del arbol AVL es: " + arbol.getAltura());


    }
}