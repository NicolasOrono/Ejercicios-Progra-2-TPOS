package main;

import stack.Stack;
import stack.LinkedStack;
import queue.Queue;
import queue.LinkedQueue;

public class UnionPilas {

  
    public static Stack<Queue<Integer>> unirPilas(Stack<Queue<Integer>> P1, Stack<Queue<Integer>> P2) {

        Stack<Queue<Integer>> Pout_Reversa = new LinkedStack<>();


        while (!P1.isEmpty() && !P2.isEmpty()) {
            if (P1.top().size() > P2.top().size()) {
                Pout_Reversa.push(P1.pop());
            } else {
                Pout_Reversa.push(P2.pop());
            }
        }

        while (!P1.isEmpty()) {
            Pout_Reversa.push(P1.pop());
        }

        while (!P2.isEmpty()) {
            Pout_Reversa.push(P2.pop());
        }



        Stack<Queue<Integer>> Pout = new LinkedStack<>();
        while (!Pout_Reversa.isEmpty()) {
            Pout.push(Pout_Reversa.pop());
        }

        return Pout;
    }

    // --- Programa Principal ---

    public static void main(String[] args) {
        // 1. Crear las colas de prueba
        Queue<Integer> q1 = createQueue(1); // tamaño 1
        Queue<Integer> q2 = createQueue(2); // tamaño 2
        Queue<Integer> q5 = createQueue(5); // tamaño 5
        Queue<Integer> q6 = createQueue(6); // tamaño 6
        Queue<Integer> q10 = createQueue(10); // tamaño 10
        Queue<Integer> q12 = createQueue(12); // tamaño 12

        Stack<Queue<Integer>> P1 = new LinkedStack<>();
        Stack<Queue<Integer>> P2 = new LinkedStack<>();

        P1.push(q1);
        P1.push(q5);
        P1.push(q10);


        P2.push(q2);
        P2.push(q6);
        P2.push(q12);

        System.out.println("--- Pilas Originales ---");
        System.out.print("Pila 1: ");
        imprimirPila(P1);
        System.out.print("Pila 2: ");
        imprimirPila(P2);


        Stack<Queue<Integer>> Pout = unirPilas(P1, P2);

        System.out.println("\n--- Pila Resultante ---");
        System.out.print("Pila Pout: ");
        imprimirPila(Pout);
    }


    private static Queue<Integer> createQueue(int size) {
        Queue<Integer> q = new LinkedQueue<>();
        for (int i = 0; i < size; i++) {
            q.enqueue(i);
        }
        return q;
    }


    public static void imprimirPila(Stack<Queue<Integer>> pila) {
        Stack<Queue<Integer>> temp = new LinkedStack<>();

        System.out.print("[Fondo] ");

        while (!pila.isEmpty()) {
            temp.push(pila.pop());
        }


        while (!temp.isEmpty()) {
            Queue<Integer> q = temp.pop();
            System.out.print("Cola(tamaño=" + q.size() + ") ");
            pila.push(q);
        }
        System.out.println("[Tope]");
    }
}