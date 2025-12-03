package tda.implementaciones;

import tda.interfaces.ABBTDA;
import tda.interfaces.ListaTDA;
import tda.nodos.NodoABB;

public class ABB<E extends Comparable<E>> implements ABBTDA<E> {

    private NodoABB<E> raiz;
    private int contador;

    public ABB() {
        this.raiz = null;
        this.contador = 0;
    }

    @Override
    public boolean estaVacio() {
        return this.raiz == null;
    }

    @Override
    public void insertar(E x) {
        this.raiz = insertarRecursivo(this.raiz, x);
    }

    private NodoABB<E> insertarRecursivo(NodoABB<E> nodo, E x) {
        if (nodo == null) {
            this.contador++;
            return new NodoABB<>(x);
        }
        int comparacion = x.compareTo(nodo.getInfo());
        if (comparacion < 0) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), x));
        } else if (comparacion > 0) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), x));
        }
        return nodo;
    }

    @Override
    public boolean pertenece(E x) {
        return perteneceRecursivo(this.raiz, x);
    }

    private boolean perteneceRecursivo(NodoABB<E> nodo, E x) {
        if (nodo == null) return false;
        int comparacion = x.compareTo(nodo.getInfo());
        if (comparacion == 0) return true;
        return (comparacion < 0)
                ? perteneceRecursivo(nodo.getIzquierdo(), x)
                : perteneceRecursivo(nodo.getDerecho(), x);
    }

    @Override
    public ListaTDA<E> getElementosInOrder() {
        ListaTDA<E> lista = new ListaDinamica<>();
        inOrder(this.raiz, lista);
        return lista;
    }

    private void inOrder(NodoABB<E> nodo, ListaTDA<E> lista) {
        if (nodo != null) {
            inOrder(nodo.getIzquierdo(), lista);
            lista.agregar(nodo.getInfo());
            inOrder(nodo.getDerecho(), lista);
        }
    }

    @Override
    public void eliminar(E x) {
        if (pertenece(x)) {
            this.raiz = eliminarRecursivo(this.raiz, x);
            this.contador--;
        }
    }

    private NodoABB<E> eliminarRecursivo(NodoABB<E> nodo, E x) {
        if (nodo == null) return null;

        int comparacion = x.compareTo(nodo.getInfo());

        if (comparacion < 0) {
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), x));
        } else if (comparacion > 0) {
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), x));
        } else {

            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                return null;
            }

            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }

            E sucesor = encontrarMinimo(nodo.getDerecho());
            nodo.setInfo(sucesor);
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), sucesor));
        }
        return nodo;
    }

    private E encontrarMinimo(NodoABB<E> nodo) {
        return (nodo.getIzquierdo() == null)
                ? nodo.getInfo()
                : encontrarMinimo(nodo.getIzquierdo());
    }
}