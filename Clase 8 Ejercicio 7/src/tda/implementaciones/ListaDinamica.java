package tda.implementaciones;

import tda.interfaces.ListaTDA;
import tda.nodos.NodoSimple;

public class ListaDinamica<E> implements ListaTDA<E> {

    private NodoSimple<E> primero;
    private NodoSimple<E> ultimo;
    private int contador;

    public ListaDinamica() {
        this.primero = null;
        this.ultimo = null;
        this.contador = 0;
    }

    @Override
    public void agregar(E x) { // Agrega al final
        NodoSimple<E> nuevo = new NodoSimple<>(x, null);
        if (this.ultimo != null) {
            this.ultimo.setSiguiente(nuevo);
        }
        this.ultimo = nuevo;
        if (this.primero == null) {
            this.primero = this.ultimo;
        }
        this.contador++;
    }

    @Override
    public void eliminar(E x) {
        if (this.primero == null) return;

        if (this.primero.getInfo().equals(x)) {
            this.primero = this.primero.getSiguiente();
            if (this.primero == null) this.ultimo = null; // Quedó vacía
            this.contador--;
            return;
        }

        NodoSimple<E> actual = this.primero;
        while (actual.getSiguiente() != null && !actual.getSiguiente().getInfo().equals(x)) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() != null) {
            NodoSimple<E> aEliminar = actual.getSiguiente();
            if (aEliminar == this.ultimo) this.ultimo = actual;
            actual.setSiguiente(aEliminar.getSiguiente());
            this.contador--;
        }
    }

    @Override
    public E obtener(int pos) {
        if (pos < 0 || pos >= this.contador) {
            System.err.println("Error: Posicion fuera de rango.");
            return null;
        }
        NodoSimple<E> actual = this.primero;
        for (int i = 0; i < pos; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getInfo();
    }

    public E buscar(E x) {
        NodoSimple<E> actual = this.primero;
        while (actual != null) {
            if (actual.getInfo().equals(x)) {
                return actual.getInfo();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public int tamanio() { return this.contador; }
    @Override
    public boolean estaVacia() { return this.contador == 0; }
}

