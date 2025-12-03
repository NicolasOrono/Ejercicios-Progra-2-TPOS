package tda.nodos;

public class NodoABB<E extends Comparable<E>> {
    private E info;
    private NodoABB<E> izquierdo;
    private NodoABB<E> derecho;

    public NodoABB(E info) {
        this.info = info;
        this.izquierdo = null;
        this.derecho = null;
    }

    public E getInfo() { return info; }
    public void setInfo(E info) { this.info = info; }
    public NodoABB<E> getIzquierdo() { return izquierdo; }
    public void setIzquierdo(NodoABB<E> izquierdo) { this.izquierdo = izquierdo; }
    public NodoABB<E> getDerecho() { return derecho; }
    public void setDerecho(NodoABB<E> derecho) { this.derecho = derecho; }
}