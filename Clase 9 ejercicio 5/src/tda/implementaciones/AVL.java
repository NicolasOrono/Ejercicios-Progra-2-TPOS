package tda.implementaciones;

import tda.interfaces.AVLTDA;
import tda.nodos.NodoAVL;

public class AVL<E extends Comparable<E>> implements AVLTDA<E> {

    private NodoAVL<E> raiz;

    public AVL() {
        this.raiz = null;
    }

    @Override
    public boolean estaVacio() {
        return this.raiz == null;
    }


    @Override
    public int getAltura() {
        if (this.raiz == null) {
            return 0;
        }
        return this.raiz.getAltura();
    }


    @Override
    public void insertar(E x) {
        this.raiz = insertarRecursivo(this.raiz, x);
    }

    private NodoAVL<E> insertarRecursivo(NodoAVL<E> nodo, E x) {
        if (nodo == null) {
            return new NodoAVL<>(x);
        }

        int comparacion = x.compareTo(nodo.getInfo());

        if (comparacion < 0) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), x));
        } else if (comparacion > 0) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), x));
        } else {
            return nodo;
        }


        actualizarAltura(nodo);

        int fb = factorBalance(nodo);


        if (fb > 1 && x.compareTo(nodo.getIzquierdo().getInfo()) < 0) {
            return rotacionDerecha(nodo);
        }

        if (fb < -1 && x.compareTo(nodo.getDerecho().getInfo()) > 0) {
            return rotacionIzquierda(nodo);
        }

        if (fb > 1 && x.compareTo(nodo.getIzquierdo().getInfo()) > 0) {
            nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
            return rotacionDerecha(nodo);
        }

        if (fb < -1 && x.compareTo(nodo.getDerecho().getInfo()) < 0) {
            nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }


    private int altura(NodoAVL<E> nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.getAltura();
    }


    private void actualizarAltura(NodoAVL<E> nodo) {
        if (nodo != null) {
            nodo.setAltura(1 + max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));
        }
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }


    private int factorBalance(NodoAVL<E> nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }


    private NodoAVL<E> rotacionDerecha(NodoAVL<E> y) {
        NodoAVL<E> x = y.getIzquierdo();
        NodoAVL<E> T2 = x.getDerecho();

        x.setDerecho(y);
        y.setIzquierdo(T2);

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }


    private NodoAVL<E> rotacionIzquierda(NodoAVL<E> x) {
        NodoAVL<E> y = x.getDerecho();
        NodoAVL<E> T2 = y.getIzquierdo();

        y.setIzquierdo(x);
        x.setDerecho(T2);

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }
}