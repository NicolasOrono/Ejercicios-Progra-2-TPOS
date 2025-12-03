package main;

import tda.implementaciones.Dictionary;
import tda.interfaces.DictionaryTDA;
import tda.interfaces.ListaTDA;

public class Main {

    public static void main(String[] args) {

        DictionaryTDA<String, Integer> agenda = new Dictionary<>();

        System.out.println("--- Agregando contactos ---");
        agenda.put("Juan", 112233);
        agenda.put("Ana", 445566);
        agenda.put("Juan", 778899); // Agregamos otro numero a Juan
        agenda.put("Pedro", 123456);
        agenda.put("Juan", 111111); // Tercer numero de Juan

        System.out.println("Cantidad de claves (contactos): " + agenda.size()); // Deberia ser 3

        System.out.println("\n--- Obteniendo numeros ---");
        System.out.println("Numeros de Juan (deberian salir 3 ordenados):");
        imprimirLista(agenda.get("Juan"));

        System.out.println("\nNumeros de Ana (deberia salir 1):");
        imprimirLista(agenda.get("Ana"));

        System.out.println("\nNumeros de 'Maria' (deberia ser null):");
        imprimirLista(agenda.get("Maria")); // null

        System.out.println("\n--- Eliminando valores especificos ---");
        System.out.println("Eliminando '778899' de Juan...");
        agenda.remove("Juan", 778899);

        System.out.println("Nuevos numeros de Juan (deberian salir 2):");
        imprimirLista(agenda.get("Juan"));

        System.out.println("\nEliminando '445566' de Ana...");
        agenda.remove("Ana", 445566);
        System.out.println("Claves restantes: " + agenda.size()); // Deberia ser 2 (Ana se elimino)

        System.out.println("Buscando a Ana... (deberia ser null):");
        imprimirLista(agenda.get("Ana")); // null

        System.out.println("\n--- Eliminando clave completa ---");
        System.out.println("Eliminando a 'Pedro'...");
        agenda.remove("Pedro");
        System.out.println("Claves restantes: " + agenda.size()); // Deberia ser 1 (solo Juan)
    }

    public static <E> void imprimirLista(ListaTDA<E> lista) {
        if (lista == null) {
            System.out.println("(null)");
            return;
        }
        if (lista.estaVacia()) {
            System.out.println("[]");
            return;
        }

        System.out.print("[ ");
        for (int i = 0; i < lista.tamanio(); i++) {
            System.out.print(lista.obtener(i));
            if (i < lista.tamanio() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }
}