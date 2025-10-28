package main;

import dictionary_simple.Map;
import dictionary_simple.LinkedMap;
import dictionary_simple.Entry;
import dictionary_multiple.Dictionary;
import dictionary_multiple.LinkedDictionary;
import list.Lista;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ProcesarNotas {

    // Variables principales
    private static Dictionary<Integer, Integer> diccionarioGeneral = new LinkedDictionary<>();
    private static Map<Integer, Integer> ultimoMapeo = null;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.println("--- Bienvenido al Sistema de Gestión Académica (Dinámico) ---");
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            String opcion = reader.readLine();
            switch (opcion) {
                case "1": cargarMapeo(); break;
                case "2": mostrarMapeo(); break;
                case "3": agregarMapeoAlGeneral(); break;
                case "4": agregarNota(); break;
                case "5": quitarNota(); break;
                case "6": quitarAlumno(); break;
                case "7": mostrarNotasAlumno(); break;
                case "8": mostrarTodosAlumnos(); break;
                case "9": mostrarPromedios(); break;
                case "0": salir = true; break;
                default: System.out.println("Opción no válida.");
            }
            if (!salir) {
                System.out.println("\n(Presione Enter para continuar...)");
                reader.readLine();
            }
        }
        System.out.println("--- Gracias por usar el sistema ---");
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Cargar un mapeo con notas de una materia");
        System.out.println("2. Mostrar el último mapeo cargado");
        System.out.println("3. Agregar el último mapeo cargado al Diccionario general");
        System.out.println("4. Agregar una nota para un DNI específico");
        System.out.println("5. Quitar una nota para un DNI específico");
        System.out.println("6. Quitar un alumno");
        System.out.println("7. Mostrar las notas de un alumno");
        System.out.println("8. Mostrar todos los alumnos");
        System.out.println("9. Mostrar todos los alumnos y su promedio de notas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void cargarMapeo() throws IOException {
        ultimoMapeo = new LinkedMap<>();
        System.out.println("--- Cargando Nuevo Mapeo ---");
        System.out.println("(Escriba 'fin' como DNI para terminar)");
        while (true) {
            System.out.print("Ingrese DNI: ");
            String dniStr = reader.readLine();
            if (dniStr.equals("fin")) break;

            System.out.print("Ingrese Nota: ");
            String notaStr = reader.readLine();

            try {
                int dni = Integer.parseInt(dniStr);
                int nota = Integer.parseInt(notaStr);
                ultimoMapeo.put(dni, nota);
                System.out.println("-> DNI " + dni + " cargado.");
            } catch (Exception e) {
                System.out.println("Error: DNI y Nota deben ser números.");
            }
        }
        System.out.println("Mapeo temporal cargado.");
    }

    private static void mostrarMapeo() {
        if (ultimoMapeo == null || ultimoMapeo.isEmpty()) {
            System.out.println("No hay ningún mapeo temporal cargado.");
        } else {
            System.out.println("--- Último Mapeo Cargado ---");
            System.out.println(ultimoMapeo);
        }
    }

    private static void agregarMapeoAlGeneral() {
        if (ultimoMapeo == null || ultimoMapeo.isEmpty()) {
            System.out.println("No hay mapeo para agregar. Cargue uno primero.");
            return;
        }

        Lista<Entry<Integer, Integer>> entries = ultimoMapeo.entries();
        for (int i = 0; i < entries.size(); i++) {
            Entry<Integer, Integer> entry = entries.get(i);
            diccionarioGeneral.put(entry.getKey(), entry.getValue());
        }

        System.out.println("Mapeo consolidado en el diccionario general.");
        System.out.println(diccionarioGeneral);
        ultimoMapeo = null; // Limpiamos el mapeo temporal
    }

    private static void agregarNota() throws IOException {
        System.out.print("Ingrese DNI: ");
        int dni = Integer.parseInt(reader.readLine());
        System.out.print("Ingrese Nota: ");
        int nota = Integer.parseInt(reader.readLine());

        diccionarioGeneral.put(dni, nota);
        System.out.println("Nota agregada.");
    }

    private static void quitarNota() throws IOException {
        System.out.print("Ingrese DNI: ");
        int dni = Integer.parseInt(reader.readLine());
        System.out.print("Ingrese Nota a quitar: ");
        int nota = Integer.parseInt(reader.readLine());

        if (diccionarioGeneral.remove(dni, nota)) {
            System.out.println("Nota quitada.");
        } else {
            System.out.println("No se encontró esa nota para el DNI especificado.");
        }
    }

    private static void quitarAlumno() throws IOException {
        System.out.print("Ingrese DNI del alumno a quitar: ");
        int dni = Integer.parseInt(reader.readLine());
        Lista<Integer> notas = diccionarioGeneral.remove(dni);
        if (notas != null) {
            System.out.println("Alumno con DNI " + dni + " eliminado. Sus notas eran: " + notas);
        } else {
            System.out.println("No se encontró un alumno con ese DNI.");
        }
    }

    private static void mostrarNotasAlumno() throws IOException {
        System.out.print("Ingrese DNI: ");
        int dni = Integer.parseInt(reader.readLine());
        Lista<Integer> notas = diccionarioGeneral.get(dni);
        if (notas != null && !notas.isEmpty()) {
            System.out.println("Notas para el DNI " + dni + ": " + notas);
        } else {
            System.out.println("El alumno no tiene notas cargadas o no existe.");
        }
    }

    private static void mostrarTodosAlumnos() {
        Lista<Integer> dnis = diccionarioGeneral.keys();
        if (dnis.isEmpty()) {
            System.out.println("No hay alumnos cargados en el diccionario general.");
        } else {
            System.out.println("--- Listado de Alumnos (DNIs) ---");
            System.out.println(dnis);
        }
    }

    private static void mostrarPromedios() {
        Lista<Integer> dnis = diccionarioGeneral.keys();
        if (dnis.isEmpty()) {
            System.out.println("No hay alumnos para calcular promedios.");
            return;
        }

        System.out.println("--- Promedio de Notas por Alumno ---");
        for (int i = 0; i < dnis.size(); i++) {
            int dni = dnis.get(i);
            Lista<Integer> notas = diccionarioGeneral.get(dni);

            if (notas.isEmpty()) {
                System.out.println("DNI: " + dni + " - Sin notas.");
                continue;
            }

            int suma = 0;
            for (int j = 0; j < notas.size(); j++) {
                suma += notas.get(j);
            }

            // Usamos doubles para el promedio
            double promedio = (double) suma / notas.size();
            System.out.println("DNI: " + dni + " - Promedio: " + promedio + " (Notas: " + notas + ")");
        }
    }
}